package marcel.versionmanagement.repositorys.project;

import marcel.versionmanagement.entities.GroupEntity;
import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.models.CreateProjectModel;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
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
        projectEntity.setIsFinal("false");
        projectEntity.setName(createProjectModel.getName());
        projectEntity.setPlagiaryStatus("unknown");

        UUID materialId = UUID.fromString(createProjectModel.getMaterialId());

        try{
            entityManager.createNativeQuery("insert into proiect values (?,?,?,?,?,?,?)")
                    .setParameter(1, projectEntity.getId())
                    .setParameter(2, projectEntity.getIsFinal())
                    .setParameter(3, projectEntity.getName())
                    .setParameter(4, projectEntity.getPlagiaryStatus())
                    .setParameter(5, UUID.fromString(createProjectModel.getGroupId()))
                    .setParameter(6, UUID.fromString(createProjectModel.getMaterialId()))
                    .setParameter(7, UUID.fromString(createProjectModel.getStudentId()))
                    .executeUpdate();
        } catch (TransactionRequiredException e){
            return null;
        } catch (Exception e){
            return "Invalid data";
        }
        return projectEntity.getId().toString();
    }

    @Override
    public List<ProjectEntity> getAllGroupProject(UUID id) {
        List<ProjectEntity> result = new LinkedList<>();
        try {
            TypedQuery<ProjectEntity> general = entityManager.createQuery(
                    "select p from ProjectEntity p " +
                            " join GroupEntity g on p.groupId = g.id " +
                            " where g.id = :id", ProjectEntity.class
            );
            result = general.setParameter("id", id).getResultList();
            for(ProjectEntity p : result) {
                p.setGroupId(null);
                p.setMaterieId(null);
                p.setVersionEntities(null);
            }
            return result;
        } catch (NoResultException e){
            return result;
        }
    }

    @Override
    public List<ProjectEntity> getUnfinishedProject(String studentId){
        List<ProjectEntity> result = new LinkedList<>();
        try{
            TypedQuery<ProjectEntity> projects = entityManager.createQuery(
                    "select p from ProjectEntity p where p.studentId.id = :id and p.isFinal = 'false'",
                    ProjectEntity.class
            );
            result = projects.setParameter("id", UUID.fromString(studentId)).getResultList();
            for(ProjectEntity p : result){
                p.setGroupId(null);
                p.setVersionEntities(null);
                p.setStudentId(null);
                p.setMaterieId(null);
            }
            return result;
        } catch (NoResultException ne){
            throw new NoResultException("Proiecte negasite");
        }
    }

    @Override
    public List<ProjectEntity> getFinishedProject(String studentId){
        List<ProjectEntity> result = new LinkedList<>();
        try{
            TypedQuery<ProjectEntity> projects = entityManager.createQuery(
                    "select p from ProjectEntity p where p.groupId.id = :id and p.isFinal != 'false'",
                    ProjectEntity.class
            );
            result = projects.setParameter("id", UUID.fromString(studentId)).getResultList();
            for(ProjectEntity p : result){
                p.setGroupId(null);
                p.setVersionEntities(null);
                p.getStudentId().getPerson().setAccountId(null);
                p.setMaterieId(null);
            }
            return result;
        } catch (NoResultException ne){
            throw new NoResultException("Proiecte negasite");
        }
    }

    private void hideGroupInformation(GroupEntity e){
        e.setCreator(null);
        e.setGroupMember(null);
    }
}
