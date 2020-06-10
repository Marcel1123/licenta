package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;
import marcel.pirlog.licenta.userManagement.exceptions.NotFoundException;
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

    @RequestMapping(method = RequestMethod.POST, value = "/student")
    public ResponseEntity getStudentByCredential(@RequestBody LoginModel loginModel) throws NotFoundException {
        StudentEntity accountEntity = accountService.findByCredential(loginModel.username, loginModel.password);
        if(accountEntity == null){
            throw new NotFoundException("Account not found!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountEntity);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teacher")
    public ResponseEntity getTeacherByCredential(@RequestBody LoginModel loginModel) throws NotFoundException {
        TeacherEntity teacherEntity = accountService.findTeacher(loginModel.username, loginModel.password);
        if(teacherEntity == null){
            throw new NotFoundException("Account not found!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherEntity);
    }

}
