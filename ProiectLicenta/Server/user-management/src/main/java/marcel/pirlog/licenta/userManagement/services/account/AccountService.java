package marcel.pirlog.licenta.userManagement.services.account;

import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;
import marcel.pirlog.licenta.userManagement.repositorys.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public StudentEntity findByCredential(String username, String password) {
        return accountRepository.findByCredential(username, password);
    }

    @Override
    public TeacherEntity findTeacher(String username, String password) {
        return accountRepository.findTeacher(username, password);
    }
}
