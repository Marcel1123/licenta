package marcel.versionmanagement.repositorys.versions;

import marcel.versionmanagement.entities.SubVersionContentEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.models.VersionModel;

import java.util.List;
import java.util.UUID;

public interface IVersionRepository {
    String addVersion(VersionModel subVersionEntity);
    String addFinalVersion(VersionModel subVersionEntity);
    List<SubVersionEntity> getProject(String id);
    List<SubVersionContentEntity> getById(String id);
    SubVersionEntity getSubById(UUID id);
}
