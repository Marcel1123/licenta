package Marcel.versionsystem.services;

import Marcel.versionsystem.entities.ProjectEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service("ProjectService")
public class ProjectService implements IProjectService {

    ArrayList<ProjectEntity> projectEntities = new ArrayList<>();

    @Override
    public ProjectEntity findById(long projectId) {
        projectEntities.add(new ProjectEntity(2, new ArrayList<>()));
        projectEntities.add(new ProjectEntity(3, new ArrayList<>()));
        projectEntities.add(new ProjectEntity(4, new ArrayList<>()));
        projectEntities.add(new ProjectEntity(5, new ArrayList<>()));
        projectEntities.add(new ProjectEntity(6, new ArrayList<>()));
        projectEntities.add(new ProjectEntity(7, new ArrayList<>()));
        final ProjectEntity[] returnres = {new ProjectEntity()};
        Predicate<ProjectEntity> predicate = new Predicate<ProjectEntity>() {
            @Override
            public boolean test(ProjectEntity project) {
                return project.getId() == projectId;
            }
        };
        projectEntities.stream()
                .filter(predicate)
                .forEach(projectEntity -> {
                    returnres[0] = projectEntity;
                });
        return returnres[0];
    }

    @Override
    public List<ProjectEntity> findAll() {
        return projectEntities;
    }

    @Override
    public boolean projectExist(long projectId) {
        return false;
    }

    @Override
    public void add(ProjectEntity projectEntity) {
        projectEntities.add(projectEntity);
    }

    @Override
    public void update(long projectId, ProjectEntity projectEntity) {

    }
}
