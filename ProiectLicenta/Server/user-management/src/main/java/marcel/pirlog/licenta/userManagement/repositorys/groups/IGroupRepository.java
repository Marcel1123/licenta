package marcel.pirlog.licenta.userManagement.repositorys.groups;

import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface IGroupRepository {
    List<StudentGroupModel> findStudentGroups(UUID studentId);
}
