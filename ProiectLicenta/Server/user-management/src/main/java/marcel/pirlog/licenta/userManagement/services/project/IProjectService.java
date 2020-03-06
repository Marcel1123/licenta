package marcel.pirlog.licenta.userManagement.services.project;

import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;

public interface IProjectService {
    String addProject(CreateProjectModel projectModel);
}
