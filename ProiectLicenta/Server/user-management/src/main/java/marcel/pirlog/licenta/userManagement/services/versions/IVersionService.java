package marcel.pirlog.licenta.userManagement.services.versions;

import marcel.pirlog.licenta.userManagement.entities.SubVersionEntity;
//import marcel.pirlog.licenta.userManagement.models.VersionModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface IVersionService {
    String addVersion(SubVersionEntity versionModel);
    String addFinalVersion(SubVersionEntity subVersionEntity);
}
