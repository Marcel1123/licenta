package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import marcel.pirlog.licenta.userManagement.services.groups.IGroupService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity addGroup(@RequestBody CreateGroupModel createGroupModel){
        if(createGroupModel.getTeacherId().equals(new UUID(0L, 0L))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else if(createGroupModel.getTeacherId() == null || createGroupModel.getName() == null
                || createGroupModel.getName().equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            GroupEntity groupEntity = groupService.addGroup(createGroupModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupEntity);
        }
    }

    @RequestMapping(value = "/teacher/{teacherId}", method = RequestMethod.GET)
    public ResponseEntity getTeacherGroups(@PathVariable UUID teacherId){
        if (teacherId.equals(new UUID(0L, 0L))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else if(teacherId == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            List<GroupEntity> groupEntityList = groupService.findTeacherGroups(teacherId);
            return ResponseEntity.status(HttpStatus.OK).body(groupEntityList.toArray());
        }
    }
}
