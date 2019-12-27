package marcel.pirlog.licenta.userManagement.services.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
//import marcel.pirlog.licenta.userManagement.repositorys.account.AccountRepository;
import marcel.pirlog.licenta.userManagement.repositorys.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<AccountEntity> findAll() {
        return accountRepository.listAccounts();
    }

    @Override
    public AccountEntity findByCredential(String username, String password) {
        return accountRepository.findByCredential(username, password);
    }
}