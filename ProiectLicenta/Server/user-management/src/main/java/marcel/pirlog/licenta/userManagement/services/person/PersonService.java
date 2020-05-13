package marcel.pirlog.licenta.userManagement.services.person;

import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.repositorys.person.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Component
@Service
public class PersonService implements IPersonService {
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<PersonEntity> getAllStudents() {
        return personRepository.getAllStudents();
    }

    @Override
    public StudentEntity getStudentById(UUID id){
        return personRepository.getStudentById(id);
    }
}
