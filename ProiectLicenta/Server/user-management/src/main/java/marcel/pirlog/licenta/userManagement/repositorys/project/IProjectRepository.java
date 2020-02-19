package marcel.pirlog.licenta.userManagement.repositorys.project;

import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IProjectRepository {
    void createProject(CreateProjectModel createProjectModel);
}
