package marcel.pirlog.licenta.userManagement.services.project;

import marcel.pirlog.licenta.userManagement.entities.ProjectEntity;
import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import marcel.pirlog.licenta.userManagement.models.GeneralProjectInformationModel;
import marcel.pirlog.licenta.userManagement.repositorys.project.IProjectRepository;
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
