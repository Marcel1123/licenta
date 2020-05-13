package marcel.pirlog.licenta.userManagement.repositorys.person;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
@Repository
public class PersonRepository implements IPersonRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<PersonEntity> getAllStudents() {
        try {
            TypedQuery<PersonEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select pe from StudentEntity ste " +
                            " join PersonEntity pe on pe.id = ste.person",
                    PersonEntity.class
            );
            List<PersonEntity> p = groupEntityTypedQuery.getResultList();
            for(PersonEntity p1 : p) p1.setAccountId(new AccountEntity());
            return p;
        } catch (NoResultException nre){
            return new LinkedList<>();
        }
    }

    @Override
    public TeacherEntity getTeacherById(UUID id) {
        try{
            TypedQuery<TeacherEntity> teacher = entityManager.createQuery(
                    "select t from TeacherEntity t " +
                            "where t.id = :id",
                    TeacherEntity.class
            );
            return teacher.setParameter("id", id).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public StudentEntity getStudentById(UUID id){
        try{
            TypedQuery<StudentEntity> studs = entityManager.createQuery(
                    "select s from StudentEntity s " +
                            " where s.person.id = :ids",
                    StudentEntity.class
            );
            StudentEntity s = studs.setParameter("ids", id).getSingleResult();
            s.getPerson().setAccountId(new AccountEntity());
            return s;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<StudentEntity> getAllStudentsStd(){
        try{
            TypedQuery<StudentEntity> std = entityManager.createQuery(
                    "select s from StudentEntity s",
                    StudentEntity.class
            );
            return std.getResultList();
        } catch (NoResultException e){
            return new LinkedList<>();
        }
    }
}
