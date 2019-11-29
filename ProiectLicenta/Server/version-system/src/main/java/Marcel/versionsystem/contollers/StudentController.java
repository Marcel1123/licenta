package Marcel.versionsystem.contollers;

import Marcel.versionsystem.entities.Student;
import Marcel.versionsystem.repositorys.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/")
    public List<Student> getAllMethod(){
        return studentRepository.retrieve();
    }
}
