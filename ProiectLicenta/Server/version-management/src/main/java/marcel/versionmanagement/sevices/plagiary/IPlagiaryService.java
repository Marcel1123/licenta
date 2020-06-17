package marcel.versionmanagement.sevices.plagiary;

import marcel.versionmanagement.entities.ProjectEntity;

import java.util.UUID;

public interface IPlagiaryService {
    void plagiary(ProjectEntity[] uuids);
}
