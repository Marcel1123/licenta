package marcel.pirlog.licenta.userManagement.repositorys.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.TeacherEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAccountRepository  {
    void add(AccountEntity entity);
    List<AccountEntity> listAccounts();
    StudentEntity findByCredential(String username, String password);
    TeacherEntity findTeacher(String username, String password);
}
