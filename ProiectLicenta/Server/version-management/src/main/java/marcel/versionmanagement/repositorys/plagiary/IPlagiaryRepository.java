package marcel.versionmanagement.repositorys.plagiary;

import marcel.versionmanagement.entities.ProjectEntity;

public interface IPlagiaryRepository {
    void plagiary(ProjectEntity[] uuids);
}
