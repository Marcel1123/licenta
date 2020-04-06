package marcel.pirlog.licenta.userManagement.services.project;

import marcel.pirlog.licenta.userManagement.entities.ProjectEntity;
import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import marcel.pirlog.licenta.userManagement.models.GeneralProjectInformationModel;

import java.util.List;
import java.util.UUID;

public interface IProjectService {
    String addProject(CreateProjectModel projectModel);
    List<ProjectEntity> getAllGroupProject(UUID id);
}
