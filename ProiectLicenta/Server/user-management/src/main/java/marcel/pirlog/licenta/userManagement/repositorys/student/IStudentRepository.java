package marcel.pirlog.licenta.userManagement.repositorys.student;

import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStudentRepository {
    void addStudent(StudentEntity studentEntity);
    StudentEntity findById(UUID id);
    StudentEntity findByAccountId(UUID accountId);
}
