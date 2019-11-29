package Marcel.versionsystem.contollers;

import Marcel.versionsystem.entities.Project;
import Marcel.versionsystem.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService = new ProjectService();

    @GetMapping(value="/{id}")
    public Project getMethod(@PathVariable("id") long id){
        return projectService.findById(id);
    }

    @GetMapping(value = "/")
    public List<Project> getAllMethod(){
        return projectService.findAll();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public void add(@RequestBody Project project){
        projectService.add(project);
    }
}
