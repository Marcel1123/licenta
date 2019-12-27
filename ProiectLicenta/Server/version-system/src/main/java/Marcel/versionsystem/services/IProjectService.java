package Marcel.versionsystem.services;

import Marcel.versionsystem.entities.ProjectEntity;

import java.util.List;

public interface IProjectService {
    ProjectEntity findById(long projectId);

    List<ProjectEntity> findAll();

    boolean projectExist(long projectId);

    void add(ProjectEntity projectEntity);

    void update(long projectId, ProjectEntity projectEntity);
}
