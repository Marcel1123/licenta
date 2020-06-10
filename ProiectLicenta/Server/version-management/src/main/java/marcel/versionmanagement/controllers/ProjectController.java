package marcel.versionmanagement.controllers;

import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.models.CreateProjectModel;
import marcel.versionmanagement.sevices.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@ControllerAdvice
@RequestMapping("/project")
public class ProjectController {
    private IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addProject(@RequestBody CreateProjectModel createProjectModel) {
        String id = projectService.addProject(createProjectModel);
        if(id.equals("Invalid data")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        }
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllGroupProject(@PathVariable String id) {
        if (id == null || id.equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        UUID uuid = UUID.fromString(id);
        if (uuid.equals(new UUID(0L, 0L))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        final List<ProjectEntity> result = projectService.getAllGroupProject(uuid);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        if (result.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("[]");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.toArray(new ProjectEntity[0]));
    }
}
