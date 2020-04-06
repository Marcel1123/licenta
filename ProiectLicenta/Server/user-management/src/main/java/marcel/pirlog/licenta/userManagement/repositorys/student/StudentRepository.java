package marcel.pirlog.licenta.userManagement.repositorys.student;

import marcel.pirlog.licenta.userManagement.entities.ProjectEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.models.GeneralProjectInformationModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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