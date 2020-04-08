package marcel.pirlog.licenta.userManagement.repositorys.versions;

import marcel.pirlog.licenta.userManagement.entities.SubVersionEntity;
import marcel.pirlog.licenta.userManagement.models.VersionModel;

import java.util.List;

public interface IVersionRepository {
    String addVersion(VersionModel subVersionEntity);
    String addFinalVersion(VersionModel subVersionEntity);
    List<SubVersionEntity> getProject(String id);
}
