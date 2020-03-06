package marcel.pirlog.licenta.userManagement.services.project;

import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import marcel.pirlog.licenta.userManagement.repositorys.project.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public String addProject(CreateProjectModel projectModel) {
        return projectRepository.createProject(projectModel);
    }
}
