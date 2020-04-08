package marcel.pirlog.licenta.userManagement.repositorys.versions;

import marcel.pirlog.licenta.userManagement.entities.SubVersionEntity;
import marcel.pirlog.licenta.userManagement.models.VersionModel;
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
        subVersionEntity.setProjectId(versionModel.getProjectId());
        subVersionEntity.setVersionId(UUID.randomUUID());
        subVersionEntity.setUploadDate(LocalDateTime.now());
        for(String string : versionModel.getCodeFile()){
            subVersionEntity.setFile(string);
            subVersionEntity.setId(UUID.randomUUID());

            try{
                entityManager.createNativeQuery("insert into versiuni values(?,?,?,?,?)")
                        .setParameter(1, subVersionEntity.getId())
                        .setParameter(2, subVersionEntity.getFile())
                        .setParameter(3, subVersionEntity.getProjectId())
                        .setParameter(4, subVersionEntity.getUploadDate())
                        .setParameter(5, subVersionEntity.getVersionId())
                        .executeUpdate();
            } catch (TransactionalException e){
                return null;
            }
        }
        return subVersionEntity.getVersionId().toString();
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
                "select s from SubVersionEntity s where s.projectId = :id",
                SubVersionEntity.class
        );
        try {
            result = query.setParameter("id", UUID.fromString(id))
                        .getResultList();
        } catch (NoResultException ne){
            return result;
        }
        return result;
    }
}
