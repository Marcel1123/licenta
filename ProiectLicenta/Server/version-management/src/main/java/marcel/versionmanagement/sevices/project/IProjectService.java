package marcel.versionmanagement.sevices.project;

import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.models.CreateProjectModel;

import java.util.List;
import java.util.UUID;

public interface IProjectService {
    String addProject(CreateProjectModel projectModel);
    List<ProjectEntity> getAllGroupProject(UUID id);
    List<ProjectEntity> getUnfinishedProject(String studentId);
}
