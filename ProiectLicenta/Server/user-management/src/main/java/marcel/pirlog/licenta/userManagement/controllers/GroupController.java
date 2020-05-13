package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.models.AddMemberModel;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.SpecialGroupModel;
import marcel.pirlog.licenta.userManagement.services.groups.IGroupService;
import marcel.pirlog.licenta.userManagement.services.person.IPersonService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ControllerAdvice
@RequestMapping(RequestPath.GROUP_ROUT)
public class GroupController {
    IGroupService groupService;
    IPersonService personService;

    @Autowired
    public GroupController(IGroupService groupService, IPersonService personService){
        this.groupService = groupService;
        this.personService = personService;
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity getStudentGroups(@PathVariable UUID studentId){
        List<GroupEntity> studentGroupModels = groupService.findStudentGroups(studentId);
        if(studentGroupModels == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nu esti in nici un grup");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentGroupModels.toArray());
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

    @RequestMapping(value = "/members/{groupId}", method = RequestMethod.GET)
    public ResponseEntity getAllStudents(@PathVariable String groupId){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getAllAvailableStudents(UUID.fromString(groupId)));
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity getGroupByName(@PathVariable String name){
        if(name.equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        GroupEntity groupEntity = groupService.getGroupByName(name);
        if(groupEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupEntity);
    }

    @RequestMapping(value = "/members/add/", method = RequestMethod.POST)
    public ResponseEntity addMember(@RequestBody AddMemberModel addMemberModel){
        if (addMemberModel.getGroupId() == null || addMemberModel.getGroupId().equals("") || addMemberModel.getGroupId().equals("00000000-0000-0000-0000-000000000000")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        if (addMemberModel.getStudentId() == null || addMemberModel.getStudentId().equals("") || addMemberModel.getStudentId().equals("00000000-0000-0000-0000-000000000000")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        if (addMemberModel.getTeacherId() == null || addMemberModel.getTeacherId().equals("") || addMemberModel.getTeacherId().equals("00000000-0000-0000-0000-000000000000")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        AddMemberModel addMemberModel1 = groupService.addMemberModel(addMemberModel);
        if(addMemberModel1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addMemberModel1);
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
            if(groupEntity == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Group already exist!");
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(groupEntity);
            }
        }
    }

    @RequestMapping(value = "/members/remove/", method = RequestMethod.POST)
    public ResponseEntity removeMember(@RequestBody AddMemberModel removeMember){
        if (removeMember.getGroupId() == null || removeMember.getGroupId().equals("") || removeMember.getGroupId().equals("00000000-0000-0000-0000-000000000000")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        if (removeMember.getStudentId() == null || removeMember.getStudentId().equals("") || removeMember.getStudentId().equals("00000000-0000-0000-0000-000000000000")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        if (removeMember.getTeacherId() == null || removeMember.getTeacherId().equals("") || removeMember.getTeacherId().equals("00000000-0000-0000-0000-000000000000")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        AddMemberModel removeMember1 = groupService.removeMemberModel(removeMember);
        if(removeMember1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(removeMember1);
    }

    @RequestMapping(value = "/gr/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupById(id.toString()));
    }



/////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}/{teacherId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@PathVariable String id, @PathVariable String teacherId){
        if(teacherId == null || teacherId.equals("00000000-0000-0000-0000-000000000000")
                || teacherId.equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        if(id == null || id.equals("00000000-0000-0000-0000-000000000000")
                || id.equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        GroupEntity groupEntity = groupService.getGroupById(id);
        if(groupEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        if(!groupEntity.getCreator().getId().toString().equals(teacherId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        String text = groupService.deleteGroup(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }
}
