package marcel.pirlog.licenta.userManagement.repositorys.project;

import marcel.pirlog.licenta.userManagement.entities.ProjectEntity;
import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import marcel.pirlog.licenta.userManagement.models.GeneralProjectInformationModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Component
public interface IProjectRepository {
    String createProject(CreateProjectModel createProjectModel);
    List<ProjectEntity> getAllGroupProject(UUID id);
}
