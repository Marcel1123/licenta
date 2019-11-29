package Marcel.versionsystem.services;

import Marcel.versionsystem.entities.Project;

import java.util.List;

public interface IProjectService {
    Project findById(long projectId);
    List<Project> findAll();
    boolean projectExist(long projectId);
    void add(Project project);
    void update(long projectId, Project project);
}
