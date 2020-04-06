package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.*;
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

    @Override
    public String deleteGroup(String name) {
        return groupRepository.deleteGroup(name);
    }

    @Override
    public GroupEntity getGroupById(String id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public GroupEntity getGroupByName(String name){
        return groupRepository.getGroupByName(name);
    }

    @Override
    public List<SpecialStudentModel> getGroupMember(UUID groupId) {
        return groupRepository.getGroupMember(groupId);
    }

    @Override
    public List<SpecialStudentModel> getAllAvailableStudents(UUID groupId) {
        return groupRepository.getAllAvailableStudents(groupId);
    }

    @Override
    public AddMemberModel addMemberModel(AddMemberModel addMemberModel){
        return groupRepository.addMemberModel(addMemberModel);
    }

    @Override
    public AddMemberModel removeMemberModel(AddMemberModel addMemberModel){
        return groupRepository.removeMemberModel(addMemberModel);
    }


}
