package marcel.pirlog.licenta.userManagement.repositorys.person;

import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;

import java.util.List;
import java.util.UUID;

public interface IPersonRepository {
    List<PersonEntity> getAllStudents();
    TeacherEntity getTeacherById(UUID id);
    StudentEntity getStudentById(UUID id);
    List<StudentEntity> getAllStudentsStd();
}
