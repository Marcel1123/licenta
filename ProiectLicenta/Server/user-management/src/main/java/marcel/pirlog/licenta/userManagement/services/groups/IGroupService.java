package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.AddMemberModel;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.SpecialStudentModel;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;

import java.util.List;
import java.util.UUID;

public interface IGroupService {
    List<StudentGroupModel> findStudentGroups(UUID studentId);
    GroupEntity addGroup(CreateGroupModel createGroupModel);
    List<GroupEntity> findTeacherGroups(UUID teacherId);
    String deleteGroup(String name);
    GroupEntity getGroupById(String id);
    GroupEntity getGroupByName(String name);
    List<SpecialStudentModel> getGroupMember(UUID groupId);
    List<SpecialStudentModel> getAllAvailableStudents(UUID groupId);
    AddMemberModel addMemberModel(AddMemberModel addMemberModel);
    AddMemberModel removeMemberModel(AddMemberModel addMemberModel);
}
