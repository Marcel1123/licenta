package marcel.pirlog.licenta.userManagement.repositorys.material;

import marcel.pirlog.licenta.userManagement.entities.MaterialEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MaterialRepository implements IMaterialRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MaterialEntity> findAll() {
        TypedQuery<MaterialEntity> materialEntityTypedQuery = entityManager.createQuery(
                "select mat from MaterialEntity mat",
                MaterialEntity.class
        );
        return materialEntityTypedQuery.getResultList();
    }
}
