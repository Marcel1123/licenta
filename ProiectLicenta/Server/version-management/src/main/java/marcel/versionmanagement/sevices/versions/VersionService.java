package marcel.versionmanagement.sevices.versions;

import marcel.versionmanagement.entities.SubVersionContentEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.models.VersionModel;
import marcel.versionmanagement.repositorys.versions.IVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class VersionService implements IVersionService {
    @Autowired
    IVersionRepository versionRepository;

    @Override
    public String addVersion(VersionModel versionModel) {
        return versionRepository.addVersion(versionModel);
    }

    @Override
    public String addFinalVersion(VersionModel subVersionEntity) {
        return versionRepository.addFinalVersion(subVersionEntity);
    }

    @Override
    public List<SubVersionEntity> getProject(String id){
        return versionRepository.getProject(id);
    }

    @Override
    public List<SubVersionContentEntity> getById(String id) {
        return versionRepository.getById(id);
    }
}
