package marcel.pirlog.licenta.userManagement.services.material;

import marcel.pirlog.licenta.userManagement.entities.MaterialEntity;
import marcel.pirlog.licenta.userManagement.repositorys.material.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MaterialService implements IMaterialService {

    @Autowired
    private IMaterialRepository materialRepository;

    @Override
    public List<MaterialEntity> findAll() {
        return materialRepository.findAll();
    }
}
