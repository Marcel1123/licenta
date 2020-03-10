package marcel.pirlog.licenta.userManagement.services.versions;

import marcel.pirlog.licenta.userManagement.models.VersionModel;

public interface IVersionService {
    String addVersion(VersionModel versionModel);
    String addFinalVersion(VersionModel subVersionEntity);
}
