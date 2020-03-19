package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import marcel.pirlog.licenta.userManagement.repositorys.groups.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class GroupService implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public List<StudentGroupModel> findStudentGroups(UUID studentId) {
        return groupRepository.findStudentGroups(studentId);
    }

    @Override
    public GroupEntity addGroup(CreateGroupModel createGroupModel) {
        return groupRepository.addGroup(createGroupModel);
    }

    @Override
    public List<GroupEntity> findTeacherGroups(UUID teacherId) {
        return groupRepository.findTeacherGroups(teacherId);
    }
}
