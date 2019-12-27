package marcel.pirlog.licenta.userManagement.repositorys.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAccountRepository  {
    void add(AccountEntity entity);
    List<AccountEntity> listAccounts();
    AccountEntity findByCredential(String username, String password);
}
