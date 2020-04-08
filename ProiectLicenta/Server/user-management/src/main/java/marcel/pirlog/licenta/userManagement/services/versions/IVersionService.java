package marcel.pirlog.licenta.userManagement.services.versions;

import marcel.pirlog.licenta.userManagement.entities.SubVersionEntity;
import marcel.pirlog.licenta.userManagement.models.VersionModel;

import java.util.List;

public interface IVersionService {
    String addVersion(VersionModel versionModel);
    String addFinalVersion(VersionModel subVersionEntity);
    List<SubVersionEntity> getProject(String id);
}
