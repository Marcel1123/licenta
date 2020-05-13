package marcel.pirlog.licenta.userManagement.services.account;

import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;

public interface IAccountService {
    public StudentEntity findByCredential(String username, String password);
    TeacherEntity findTeacher(String username, String password);
}
