package marcel.pirlog.licenta.userManagement.services.student;

import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.repositorys.student.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public StudentEntity findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public StudentEntity findByAccountId(UUID accountId) {
        return studentRepository.findByAccountId(accountId);
    }
}
