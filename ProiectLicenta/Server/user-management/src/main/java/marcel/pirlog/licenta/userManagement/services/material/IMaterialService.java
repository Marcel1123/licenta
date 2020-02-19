package marcel.pirlog.licenta.userManagement.services.material;

import marcel.pirlog.licenta.userManagement.entities.MaterialEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface IMaterialService {
    List<MaterialEntity> findAll();
}
