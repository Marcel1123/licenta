package marcel.pirlog.licenta.userManagement.services.groups;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.models.*;
import marcel.pirlog.licenta.userManagement.repositorys.groups.IGroupRepository;
import marcel.pirlog.licenta.userManagement.repositorys.person.IPersonRepository;
import marcel.pirlog.licenta.userManagement.services.person.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Component
public class GroupService implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<GroupEntity> findStudentGroups(UUID studentId) {
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
    public List<PersonEntity> getGroupMember(UUID groupId) {
        return groupRepository.getGroupMember(groupId);
    }

    @Override
    public SpecialGroupModel getAllAvailableStudents(UUID groupId) {
        SpecialGroupModel sgm = new SpecialGroupModel();
        List<PersonEntity> pers = groupRepository.getGroupMember(groupId);
        StudentEntity[] studs = new StudentEntity[pers.size()];
        for (int i = 0; i < pers.size(); i++) {
            studs[i] = personRepository.getStudentById(pers.get(i).getId());
        }
        sgm.setGroupStudents(studs);

        List<StudentEntity> p = personRepository.getAllStudentsStd();
        for(StudentEntity p1 : p)
            p1.getPerson().setAccountId(new AccountEntity());
        sgm.setAvailableStudents(p.stream()
                .filter(a -> !Arrays.asList(sgm.getGroupStudents()).contains(a))
                .toArray(StudentEntity[]::new));

        return sgm;
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
