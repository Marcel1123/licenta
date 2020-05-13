package marcel.pirlog.licenta.userManagement.repositorys.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository  {
    void add(AccountEntity entity);
    StudentEntity findByCredential(String username, String password);
    TeacherEntity findTeacher(String username, String password);

}
