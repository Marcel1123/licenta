package marcel.pirlog.licenta.userManagement.repositorys.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.person.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;
import marcel.pirlog.licenta.userManagement.utils.Hash;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class AccountRepository implements IAccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(AccountEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public StudentEntity findByCredential(String username, String password) {
        TypedQuery<StudentEntity> accountEntityTypedQuery = entityManager.createQuery(
                "select s from AccountEntity c" +
                        " join PersonEntity p on p.accountId = c.id " +
                        " join StudentEntity s on s.person = p.id where c.username = :username " +
                        " and c.password = :password", StudentEntity.class
        );
        try{
            return accountEntityTypedQuery.setParameter("username", username)
                    .setParameter("password", Hash.toHexString(Hash.getSHA(password)))
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public TeacherEntity findTeacher(String username, String password) {
        TypedQuery<TeacherEntity> teacherAccount = entityManager.createQuery(
                "select s from AccountEntity c" +
                        " join PersonEntity p on p.accountId = c.id " +
                        " join TeacherEntity s on s.person = p.id where c.username = :username " +
                        " and c.password = :password", TeacherEntity.class
        );
        try{
            return teacherAccount.setParameter("username", username)
                    .setParameter("password",  Hash.toHexString(Hash.getSHA(password)))
                    .getSingleResult();
        } catch (NoResultException ne){
            return null;
        }
    }


}
