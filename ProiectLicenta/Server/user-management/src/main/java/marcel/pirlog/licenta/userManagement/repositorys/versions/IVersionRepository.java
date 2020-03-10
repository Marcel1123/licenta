package marcel.pirlog.licenta.userManagement.repositorys.versions;

import marcel.pirlog.licenta.userManagement.models.VersionModel;

public interface IVersionRepository {
    String addVersion(VersionModel subVersionEntity);
    String addFinalVersion(VersionModel subVersionEntity);
}
