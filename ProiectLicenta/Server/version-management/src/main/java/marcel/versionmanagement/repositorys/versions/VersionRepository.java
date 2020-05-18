package marcel.versionmanagement.repositorys.versions;

import marcel.versionmanagement.entities.SubVersionContentEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.models.ContentModel;
import marcel.versionmanagement.models.VersionModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
@Repository
public class VersionRepository implements IVersionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String addVersion(VersionModel versionModel) {
        SubVersionEntity subVersionEntity = new SubVersionEntity();
        subVersionEntity.setId(UUID.randomUUID());
        subVersionEntity.setUploadDate(LocalDateTime.now());
        subVersionEntity.setCompiling("unknown");

        try{
            entityManager.createNativeQuery("insert into versiuni values (?,?,?)")
                    .setParameter(1, subVersionEntity.getId())
                    .setParameter(2, subVersionEntity.getCompiling())
                    .setParameter(3, subVersionEntity.getUploadDate())
                    .executeUpdate();
            entityManager.createNativeQuery("insert into projet_version values (?,?)")
                    .setParameter(1, versionModel.getProjectId())
                    .setParameter(2, subVersionEntity.getId())
                    .executeUpdate();
            for(ContentModel s : versionModel.getCodeFile()){
                UUID id = UUID.randomUUID();
                entityManager.createNativeQuery("insert into content values (?,?,?)")
                        .setParameter(1, id)
                        .setParameter(2, s.getfName())
                        .setParameter(3, s.getCode())
                        .executeUpdate();
                entityManager.createNativeQuery("insert into vers_content values (?,?)")
                        .setParameter(1, subVersionEntity.getId())
                        .setParameter(2, id)
                        .executeUpdate();
            }
            return subVersionEntity.getId().toString();
        } catch (TransactionalException e){
            return null;
        } catch (Exception f){
            return "Invalid data";
        }
    }

    @Override
    @Transactional
    public String addFinalVersion(VersionModel versionModel) {
        String id = addVersion(versionModel);
        if(id == null || id.isEmpty()){
            return null;
        }
        try{
            entityManager.createNativeQuery("update proiect set finalizat = 'true' where id = ?")
                    .setParameter(1, versionModel.getProjectId())
                    .executeUpdate();
        } catch (TransactionalException e){
            return null;
        }
        return id;
    }

    @Override
    public List<SubVersionEntity> getProject(String id){
        List<SubVersionEntity> result = new LinkedList<>();
        TypedQuery<SubVersionEntity> query = entityManager.createQuery(
                "select s from SubVersionEntity s where s.project.id = :id",
                SubVersionEntity.class
        );
        try {
            result = query.setParameter("id", UUID.fromString(id))
                        .getResultList();
            for(SubVersionEntity s : result){
                s.setContent(null);
                s.setProject(null);
            }
        } catch (NoResultException ne){
            return result;
        }
        return result;
    }

    @Override
    public  List<SubVersionContentEntity> getById(String id){
        TypedQuery<SubVersionContentEntity> query = entityManager.createQuery(
                "select s from SubVersionContentEntity s where s.subVersionEntities.id = :id",
                SubVersionContentEntity.class
        );
        try{
            List<SubVersionContentEntity> s = query.setParameter("id", UUID.fromString(id)).getResultList();
            for(SubVersionContentEntity se : s){
                se.setSubVersionEntities(null);
            }
            return s;
        } catch (NoResultException ne){
            return null;
        }
    }
}
