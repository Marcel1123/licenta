package marcel.pirlog.licenta.userManagement.repositorys.student;

import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.cfg.Configuration;
import java.util.UUID;

@Repository
public class StudentRepository implements IStudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addStudent(StudentEntity studentEntity) {
        entityManager.persist(studentEntity);
    }

    @Override
    public StudentEntity findById(UUID id) {
        return entityManager.find(StudentEntity.class, id);
    }

    @Override
    public StudentEntity findByAccountId(UUID accountId) {
        StudentEntity studentEntity;
        try{
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("select _student from StudentEntity _student where _student.accountId = :id_cont");
            query.setParameter("id_cont", accountId);
            studentEntity = (StudentEntity) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        return studentEntity;
    }
}