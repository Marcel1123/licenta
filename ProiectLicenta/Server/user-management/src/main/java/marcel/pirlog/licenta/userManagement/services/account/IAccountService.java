package marcel.pirlog.licenta.userManagement.services.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.TeacherEntity;

import java.util.List;

public interface IAccountService {

    public List<AccountEntity> findAll();
    public StudentEntity findByCredential(String username, String password);
    TeacherEntity findTeacher(String username, String password);
}
