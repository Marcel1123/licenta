package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import marcel.pirlog.licenta.userManagement.services.groups.IGroupService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@ControllerAdvice
@RequestMapping(RequestPath.GROUP_ROUT)
public class GroupController {
    IGroupService groupService;

    @Autowired
    public GroupController(IGroupService groupService){
        this.groupService = groupService;
    }

    @RequestMapping(value = RequestPath.GROUP_STUDENT_GET, method = RequestMethod.GET)
    public ResponseEntity getStudentGroups(@PathVariable UUID studentId){
        List<StudentGroupModel> studentGroupModels = groupService.findStudentGroups(studentId);
        if(studentGroupModels == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nu esti in nici un grup");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentGroupModels.toArray());
    }

}
