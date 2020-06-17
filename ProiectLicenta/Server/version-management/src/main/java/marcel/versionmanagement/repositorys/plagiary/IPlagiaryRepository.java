package marcel.versionmanagement.repositorys.plagiary;

import marcel.versionmanagement.entities.ProjectEntity;

import java.util.UUID;

public interface IPlagiaryRepository {
    void plagiary(ProjectEntity[] uuids);
}
