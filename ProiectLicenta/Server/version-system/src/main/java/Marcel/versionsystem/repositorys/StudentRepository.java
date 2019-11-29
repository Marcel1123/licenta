package Marcel.versionsystem.repositorys;

import Marcel.versionsystem.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    private Set<Student> repository;

    public StudentRepository() {
        this.repository = new HashSet<>();
    }

    public List<Student> retrieve() {
        return new LinkedList<>();
    }

}
