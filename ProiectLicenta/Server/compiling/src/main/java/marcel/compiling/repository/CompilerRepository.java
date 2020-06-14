package marcel.compiling.repository;

import marcel.compiling.entity.SubVersionContentEntity;
import marcel.compiling.entity.SubVersionEntity;
import marcel.compiling.entity.person.TeacherEntity;
import marcel.compiling.environement.MemoryCompilerClassLoader;
import marcel.compiling.exceptions.CompilerException;
import marcel.compiling.exceptions.InvalidObjectsException;
import marcel.compiling.exceptions.VersionAlreadyCompiledException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Component
@Repository
@Transactional
public class CompilerRepository implements ICompilerRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Base64.Decoder d = Base64.getDecoder();

    @Override
    public void compile(UUID id) throws IOException, CompilerException, VersionAlreadyCompiledException, InvalidObjectsException {
        boolean aux1 = false;
        SubVersionEntity subVersionEntity = getVersion(id);
        if(subVersionEntity != null){
            if(subVersionEntity.getCompiling().equals("success") || subVersionEntity.getCompiling().equals("failed")){
                throw new VersionAlreadyCompiledException("This version already compiled");
            }
            if(!subVersionEntity.getCompiling().equals("unknown") && !subVersionEntity.getCompiling().equals("failed") && !subVersionEntity.getCompiling().equals("success")){
                throw new InvalidObjectsException("Invalid compiling version attribute!");
            }
            for(SubVersionContentEntity svce : subVersionEntity.getContent()){
                String[] splits = svce.getfName().split("\\.");
                System.out.println(splits.length);
                MemoryCompilerClassLoader aux = new MemoryCompilerClassLoader(null, splits[0], new String(d.decode(svce.getFile())), null);
                aux1 = aux.compile(splits[0], new String(d.decode(svce.getFile())), null);
                if(!aux1){
                    updateCompileStatusFailed(subVersionEntity.getId());
                    throw new CompilerException("Compiler error");
                }
            }
            updateCompileStatusSuccess(subVersionEntity.getId());
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
            throw new NoResultException("No entity find in database!");
        }
    }

    public TeacherEntity getTeacher(String id) throws InvalidObjectsException {
        TypedQuery<TeacherEntity> query = entityManager.createQuery(
                "select s from TeacherEntity s where s.id = :id",
                TeacherEntity.class
        );
        try{
            return query.setParameter("id", UUID.fromString(id)).getSingleResult();
        } catch (NoResultException e){
            throw new NoResultException("No entity find in database!");
        } catch (IllegalArgumentException ie){
            throw new InvalidObjectsException("Invalid object");
        }
    }

    @Transactional
    public void updateCompileStatusSuccess(UUID versionId){
        try{
            entityManager.createNativeQuery("update versiuni set compiling = 'success' where id = ?")
                    .setParameter(1, versionId)
                    .executeUpdate();
        } catch (TransactionalException e){
        }
    }

    @Transactional
    public void updateCompileStatusFailed(UUID versionId){
        try{
            entityManager.createNativeQuery("update versiuni set compiling = 'failed' where id = ?")
                    .setParameter(1, versionId)
                    .executeUpdate();
        } catch (TransactionalException e){
            throw new TransactionalException("Exceptie", e);
        }
    }
}
