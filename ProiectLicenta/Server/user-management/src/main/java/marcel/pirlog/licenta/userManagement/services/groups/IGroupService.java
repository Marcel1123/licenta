package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.models.*;

import java.util.List;
import java.util.UUID;

public interface IGroupService {
    List<GroupEntity> findStudentGroups(UUID studentId);
    GroupEntity addGroup(CreateGroupModel createGroupModel);
    List<GroupEntity> findTeacherGroups(UUID teacherId);
    String deleteGroup(String name);
    GroupEntity getGroupById(String id);
    GroupEntity getGroupByName(String name);
    List<PersonEntity> getGroupMember(UUID groupId);
    SpecialGroupModel getAllAvailableStudents(UUID groupId);
    AddMemberModel addMemberModel(AddMemberModel addMemberModel);
    AddMemberModel removeMemberModel(AddMemberModel addMemberModel);
}
