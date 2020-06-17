package marcel.versionmanagement.sevices.plagiary;

import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.repositorys.plagiary.IPlagiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PlagiaryService implements IPlagiaryService {
    @Autowired
    private IPlagiaryRepository plagiaryRepository;

    @Override
    public void plagiary(ProjectEntity[] uuids) {
        plagiaryRepository.plagiary(uuids);
    }
}
