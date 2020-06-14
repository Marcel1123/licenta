package marcel.versionmanagement.repositorys.project;

import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.models.CreateProjectModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Component
public interface IProjectRepository {
    String createProject(CreateProjectModel createProjectModel);
    List<ProjectEntity> getAllGroupProject(UUID id);
    List<ProjectEntity> getUnfinishedProject(String studentId);
}
