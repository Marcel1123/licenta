package marcel.versionmanagement.sevices.versions;


import marcel.versionmanagement.entities.SubVersionContentEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.models.VersionModel;

import java.util.List;

public interface IVersionService {
    String addVersion(VersionModel versionModel);
    String addFinalVersion(VersionModel subVersionEntity);
    List<SubVersionEntity> getProject(String id);
    List<SubVersionContentEntity> getById(String id);
}
