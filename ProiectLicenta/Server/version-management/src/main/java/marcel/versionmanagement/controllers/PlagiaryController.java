package marcel.versionmanagement.controllers;

import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.sevices.plagiary.IPlagiaryService;
import marcel.versionmanagement.sevices.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@ControllerAdvice
@RequestMapping("/plagiary")
public class PlagiaryController {
    @Autowired
    private IPlagiaryService plagiaryService;
    @Autowired
    private IProjectService projectService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity plagiary(/*@RequestBody ProjectEntity[] uuids*/){
        List<ProjectEntity> projectEntities = projectService.getFinishedProject("1e8b95d3-d341-4da7-983b-e8249a7ed781");
        ProjectEntity[] projectEntities1 = new ProjectEntity[3];
        projectEntities1[0] = projectEntities.get(projectEntities.size() - 3);
        projectEntities1[1] = projectEntities.get(projectEntities.size() - 2);
        projectEntities1[2] = projectEntities.get(projectEntities.size() - 1);
        plagiaryService.plagiary(projectEntities1);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
