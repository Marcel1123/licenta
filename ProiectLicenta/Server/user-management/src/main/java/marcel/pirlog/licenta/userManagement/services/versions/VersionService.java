package marcel.pirlog.licenta.userManagement.services.versions;

import marcel.pirlog.licenta.userManagement.entities.SubVersionEntity;
//import marcel.pirlog.licenta.userManagement.models.VersionModel;
import marcel.pirlog.licenta.userManagement.models.VersionModel;
import marcel.pirlog.licenta.userManagement.repositorys.versions.IVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
}
