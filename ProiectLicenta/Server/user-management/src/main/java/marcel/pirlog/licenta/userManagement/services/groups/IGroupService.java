package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;

import java.util.List;
import java.util.UUID;

public interface IGroupService {
    List<StudentGroupModel> findStudentGroups(UUID studentId);
}
