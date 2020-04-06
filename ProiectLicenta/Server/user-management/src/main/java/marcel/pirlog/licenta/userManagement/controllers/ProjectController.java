package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.entities.ProjectEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.models.CreateProjectModel;
import marcel.pirlog.licenta.userManagement.models.GeneralProjectInformationModel;
import marcel.pirlog.licenta.userManagement.services.project.IProjectService;
import marcel.pirlog.licenta.userManagement.services.student.IStudentService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@ControllerAdvice
@RequestMapping(RequestPath.PROJECT_ROUT)
public class ProjectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private IProjectService projectService;
    private IStudentService studentService;

    @Autowired
    public ProjectController(IProjectService projectService, IStudentService studentService) {
        this.projectService = projectService;
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addProject(@RequestBody CreateProjectModel createProjectModel) {
        String id = projectService.addProject(createProjectModel);
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

        List<GeneralProjectInformationModel> final_result = new LinkedList<>();
        for(ProjectEntity p : result){
            StudentEntity s = studentService.findById(p.getStudentId());
            final_result.add(new GeneralProjectInformationModel(
                    p.getId().toString(),
                    p.getName(),
                    s.getFirstName() + ' ' + s.getLastName() + ' ' + s.getYear(),
                    p.getCompilationStatus(),
                    p.getPlagiaryStatus(),
                    p.getIsFinal()
            ));
        }

        return ResponseEntity.status(HttpStatus.OK).body(final_result.toArray(new GeneralProjectInformationModel[0]));
    }
}
