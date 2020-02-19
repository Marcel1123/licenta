package marcel.pirlog.licenta.userManagement.services.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;

import java.util.List;

public interface IAccountService {

    public List<AccountEntity> findAll();
    public StudentEntity findByCredential(String username, String password);
}
