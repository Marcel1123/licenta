package marcel.pirlog.licenta.userManagement.repositorys.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(AccountEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<AccountEntity> listAccounts() {
        CriteriaQuery<AccountEntity> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(AccountEntity.class);
        Root<AccountEntity> root = criteriaQuery.from(AccountEntity.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public StudentEntity findByCredential(String username, String password) {
        TypedQuery<StudentEntity> accountEntityTypedQuery = entityManager.createQuery(
                "select s from AccountEntity c" +
                        " join StudentEntity s on s.accountId = c.id where c.username = :username " +
                        " and c.password = :password", StudentEntity.class
        );
        try{
            return accountEntityTypedQuery.setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }


}
