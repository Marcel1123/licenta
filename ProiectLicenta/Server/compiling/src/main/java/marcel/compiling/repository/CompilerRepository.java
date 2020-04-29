package marcel.compiling.repository;

import marcel.compiling.entity.SubVersionEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Component
@Repository
public class CompilerRepository implements ICompilerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SubVersionEntity> getVersionForCompile(UUID subversionID) {
        TypedQuery<SubVersionEntity> versionEntity = entityManager.createQuery(
                "select sve from SubVersionEntity sve where sve.versionId = :id", SubVersionEntity.class
        );
        return versionEntity.setParameter("id", subversionID).getResultList();
    }
}
