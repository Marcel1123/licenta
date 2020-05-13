package marcel.pirlog.licenta.userManagement.services.person;

import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;

import java.util.List;
import java.util.UUID;

public interface IPersonService {
    List<PersonEntity> getAllStudents();
    StudentEntity getStudentById(UUID id);
}
