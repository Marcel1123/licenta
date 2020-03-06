package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import marcel.pirlog.licenta.userManagement.services.project.IProjectService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ControllerAdvice
@RequestMapping(RequestPath.PROJECT_ROUT)
public class ProjectController {

    IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService){
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addProject(@RequestBody CreateProjectModel createProjectModel){
        String id = projectService.addProject(createProjectModel);
        if(id == null || id.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
