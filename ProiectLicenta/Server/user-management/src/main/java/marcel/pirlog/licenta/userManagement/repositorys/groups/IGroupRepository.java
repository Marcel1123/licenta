package marcel.pirlog.licenta.userManagement.repositorys.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.AddMemberModel;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.SpecialStudentModel;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface IGroupRepository {
    List<StudentGroupModel> findStudentGroups(UUID studentId);
    List<GroupEntity> findTeacherGroups(UUID teacherId);
    GroupEntity addGroup(CreateGroupModel createGroupModel);
    String deleteGroup(String groupId);
    GroupEntity getGroupById(String id);
    GroupEntity getGroupByName(String name);
    List<SpecialStudentModel> getGroupMember(UUID groupId);
    List<SpecialStudentModel> getAllAvailableStudents(UUID groupId);
    AddMemberModel addMemberModel(AddMemberModel addMemberModel);
    AddMemberModel removeMemberModel(AddMemberModel addMemberModel);
}
