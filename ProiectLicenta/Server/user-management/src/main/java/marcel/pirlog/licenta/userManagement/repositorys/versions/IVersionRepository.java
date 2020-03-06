package marcel.pirlog.licenta.userManagement.repositorys.versions;

import marcel.pirlog.licenta.userManagement.entities.SubVersionEntity;
import marcel.pirlog.licenta.userManagement.models.VersionModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface IVersionRepository {
    String addVersion(SubVersionEntity subVersionEntity);
    String addFinalVersion(VersionModel versionModel);
}
