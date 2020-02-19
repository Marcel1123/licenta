package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.services.student.IStudentService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(RequestPath.STUDENT_ROUT)
public class StudentController {

    IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService){
        this.studentService = studentService;
    }

    @RequestMapping(value = RequestPath.GET_STUDENT_BY_ID, method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable UUID id){
        StudentEntity res = studentService.findByAccountId(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
