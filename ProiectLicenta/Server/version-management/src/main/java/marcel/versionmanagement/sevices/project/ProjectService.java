package marcel.versionmanagement.sevices.project;

import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.models.CreateProjectModel;
import marcel.versionmanagement.repositorys.project.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public String addProject(CreateProjectModel projectModel) {
        return projectRepository.createProject(projectModel);
    }

    @Override
    public List<ProjectEntity> getAllGroupProject(UUID id) {
        return projectRepository.getAllGroupProject(id);
    }
}
