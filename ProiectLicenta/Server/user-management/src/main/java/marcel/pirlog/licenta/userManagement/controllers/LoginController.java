package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.models.LoginModel;
import marcel.pirlog.licenta.userManagement.services.account.IAccountService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping(RequestPath.POST_CREDENTIALS)
public class LoginController {

    IAccountService accountService;

    @Autowired
    public LoginController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity getByCredential(@RequestBody LoginModel loginModel){
        AccountEntity accountEntity = accountService.findByCredential(loginModel.username, loginModel.password);
        if(accountEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        if(!accountEntity.getPassword().equals(loginModel.password)){
            return new ResponseEntity<Error>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(accountEntity.getId().toString());
    }

}
