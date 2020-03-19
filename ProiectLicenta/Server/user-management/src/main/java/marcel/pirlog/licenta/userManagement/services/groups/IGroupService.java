package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;

import java.util.List;
import java.util.UUID;

public interface IGroupService {
    List<StudentGroupModel> findStudentGroups(UUID studentId);
    GroupEntity addGroup(CreateGroupModel createGroupModel);
    List<GroupEntity> findTeacherGroups(UUID teacherId);
}
