package marcel.pirlog.licenta.userManagement.repositorys.account;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;
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
    public AccountEntity findByCredential(String username, String password) {
        TypedQuery<AccountEntity> accountEntityTypedQuery = entityManager.createQuery(
                "select c from AccountEntity c where c.username = :username", AccountEntity.class
        );
        try{
            return accountEntityTypedQuery.setParameter("username", username).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }


}
