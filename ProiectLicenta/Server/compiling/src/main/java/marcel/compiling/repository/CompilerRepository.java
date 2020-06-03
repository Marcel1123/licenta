package marcel.compiling.repository;

import marcel.compiling.entity.SubVersionContentEntity;
import marcel.compiling.entity.SubVersionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.tools.*;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.io.*;
import java.net.URI;
import java.util.*;

@Component
@Repository
public class CompilerRepository implements ICompilerRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Base64.Decoder d;
    private final JavaCompiler compiler;

    public CompilerRepository(){
        logger.info("Prepare decoder ... ");
        d = Base64.getDecoder();
        logger.info("Decoder ready ... ");
        logger.info("Prepare for create compiler instance ... ");
        this.compiler=ToolProvider.getSystemJavaCompiler();
        logger.info("Compiler created ... ");
    }

    @Override
    public void compile(UUID id) {
        SubVersionEntity subVersionEntity = getVersion(id);
        if(subVersionEntity != null){
            List<JavaFileObject> list = new LinkedList<>();
            List<File> files = new LinkedList<>();
            String uuid = createNewDir();
            int local = 0;
            for(SubVersionContentEntity s : subVersionEntity.getContent()){
                String f = createFile(uuid, s.getfName(), s.getFile());
                if(f != null && uuid != null){
                    files.add(new File(f));
                    list.add(new MemoryJavaFileObject(uuid, JavaFileObject.Kind.SOURCE));
                }
            }
            boolean res = compile(list.toArray(new JavaFileObject[0]), new File(uuid), files.toArray(new File[0]));
            deleteFolderAndContent(uuid);
        }
    }

    private String createNewDir(){
        UUID uuid = UUID.randomUUID();
        File file = new File(uuid.toString());
        while (!file.mkdir()){
            uuid = UUID.randomUUID();
            file = new File(uuid.toString());
        }
        if(file.exists()){
            return uuid.toString();
        } else {
            return null;
        }
    }

    private void deleteFolderAndContent(String dirName){
        File[] content = new File(dirName).listFiles();
        if(content != null){
            for(File f : content){
                if(f.isDirectory()){
                    deleteFolderAndContent(f.getName());
                } else {
                    f.delete();
                }
            }
        }
        new File(dirName).delete();
    }

    private String createFile(String directorName, String fileName, String fileContent){
        File file = new File(directorName + '/' + fileName);
        try {
            if(file.createNewFile()){
                FileWriter fileWriter = new FileWriter(directorName + '/' + fileName);
                fileWriter.write(new String(d.decode(fileContent)));
                fileWriter.close();
                return directorName + '/' + fileName;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SubVersionEntity getVersion(UUID id){
        TypedQuery<SubVersionEntity> query = entityManager.createQuery(
                "select s from SubVersionEntity s where s.id = :id",
                SubVersionEntity.class
        );
        try{
            return query.setParameter("id", id).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    private boolean compile(JavaFileObject[] javaFileObjects, File outLocation, File[] classLocation){
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        try{
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(outLocation));
            fileManager.setLocation(StandardLocation.CLASS_PATH, Arrays.asList(classLocation));
            return compiler.getTask(null,
                    fileManager,
                    null,
                    null,
                    null,
                    Arrays.asList(javaFileObjects)).call();
        } catch (IOException e){
            return false;
        }
    }

    @Transactional
    public void updateCompileStatus(UUID versionId, String status){
        try{
            entityManager.createNativeQuery("update versiuni set compiling = ? where id = ?")
                    .setParameter(1, status)
                    .setParameter(2, versionId)
                    .executeUpdate();
        } catch (TransactionalException e){
        }
    }

    private class MemoryJavaFileObject extends SimpleJavaFileObject {

        ByteArrayOutputStream baos;

        MemoryJavaFileObject(String name, Kind kind) {
            super(URI.create(name), kind);
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException, IllegalStateException, UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        public InputStream openInputStream() throws IOException, IllegalStateException, UnsupportedOperationException {
            return new ByteArrayInputStream(baos.toByteArray());
        }

        public OutputStream openOutputStream() throws IOException, IllegalStateException, UnsupportedOperationException {
            return baos = new ByteArrayOutputStream();
        }
    }
}
