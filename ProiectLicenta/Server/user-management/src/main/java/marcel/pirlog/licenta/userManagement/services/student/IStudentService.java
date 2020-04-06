package marcel.pirlog.licenta.userManagement.services.student;

import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IStudentService {
    StudentEntity findById(UUID id);

    StudentEntity findByAccountId(UUID accountId);


}
