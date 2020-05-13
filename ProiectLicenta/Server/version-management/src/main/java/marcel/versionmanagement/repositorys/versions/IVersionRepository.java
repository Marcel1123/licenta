package marcel.versionmanagement.repositorys.versions;

import marcel.versionmanagement.entities.SubVersionContentEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.models.VersionModel;

import java.util.List;

public interface IVersionRepository {
    String addVersion(VersionModel subVersionEntity);
    String addFinalVersion(VersionModel subVersionEntity);
    List<SubVersionEntity> getProject(String id);
    List<SubVersionContentEntity> getById(String id);
}
