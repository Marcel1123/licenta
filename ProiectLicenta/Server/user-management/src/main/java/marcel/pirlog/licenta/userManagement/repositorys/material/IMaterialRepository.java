package marcel.pirlog.licenta.userManagement.repositorys.material;

import marcel.pirlog.licenta.userManagement.entities.MaterialEntity;

import java.util.List;

public interface IMaterialRepository {
    List<MaterialEntity> findAll();
}
