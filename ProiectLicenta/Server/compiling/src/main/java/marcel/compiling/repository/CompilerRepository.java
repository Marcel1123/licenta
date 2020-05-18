package marcel.compiling.repository;

import marcel.compiling.entity.SubVersionEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.util.UUID;

@Component
@Repository
public class CompilerRepository implements ICompilerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void compile(UUID id) {
        SubVersionEntity subVersionEntity = getVersion(id);
        if(subVersionEntity != null){
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            
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
}
