package marcel.pirlog.licenta.userManagement.repositorys.project;

import marcel.pirlog.licenta.userManagement.entities.ProjectEntity;
import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Component
public class ProjectRepository implements IProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String createProject(CreateProjectModel createProjectModel) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUID.randomUUID());
        projectEntity.setCompilationStatus("unknown");
        projectEntity.setIsFinal("false");
        projectEntity.setName(createProjectModel.getName());
        projectEntity.setStudentId(UUID.fromString(createProjectModel.getStudentId()));
        projectEntity.setGroupId(UUID.fromString(createProjectModel.getGroupId()));
        projectEntity.setPlagiaryStatus("unknown");

        UUID materialId = UUID.fromString(createProjectModel.getMaterialId());

        if(materialId.equals(new UUID(0L, 0L))){
            projectEntity.setMaterieId(new UUID(0L, 0L));
        } else {
            projectEntity.setMaterieId(UUID.fromString(createProjectModel.getMaterialId()));
        }
        try{
            entityManager.createNativeQuery("insert into proiect values (?,?,?,?,?,?,?,?)")
                    .setParameter(1, projectEntity.getId())
                    .setParameter(2, projectEntity.getCompilationStatus())
                    .setParameter(3, projectEntity.getIsFinal())
                    .setParameter(4, projectEntity.getMaterieId())
                    .setParameter(5, projectEntity.getPlagiaryStatus())
                    .setParameter(6, projectEntity.getStudentId())
                    .setParameter(7, projectEntity.getName())
                    .setParameter(8, projectEntity.getGroupId())
                    .executeUpdate();
        } catch (TransactionRequiredException e){
            return null;
        }
        return projectEntity.getId().toString();
    }
}
