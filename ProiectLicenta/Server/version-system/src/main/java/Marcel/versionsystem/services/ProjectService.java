package Marcel.versionsystem.services;

import Marcel.versionsystem.entities.Project;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service("ProjectService")
public class ProjectService implements IProjectService {

    ArrayList<Project> projects = new ArrayList<>();

    @Override
    public Project findById(long projectId) {
        projects.add(new Project(2, new ArrayList<>()));
        projects.add(new Project(3, new ArrayList<>()));
        projects.add(new Project(4, new ArrayList<>()));
        projects.add(new Project(5, new ArrayList<>()));
        projects.add(new Project(6, new ArrayList<>()));
        projects.add(new Project(7, new ArrayList<>()));
        final Project[] returnres = {new Project()};
        Predicate<Project> predicate = new Predicate<Project>() {
            @Override
            public boolean test(Project project) {
                return project.getId() == projectId;
            }
        };
        projects.stream()
                .filter(predicate)
                .forEach(project -> {
                    returnres[0] = project;
                });
        return returnres[0];
    }

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public boolean projectExist(long projectId) {
        return false;
    }

    @Override
    public void add(Project project) {
        projects.add(project);
    }

    @Override
    public void update(long projectId, Project project) {

    }
}
