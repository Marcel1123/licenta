package marcel.compiling.repository;

import marcel.compiling.entity.SubVersionEntity;

import java.util.List;
import java.util.UUID;

public interface ICompilerRepository {
    List<SubVersionEntity> getVersionForCompile(UUID subversionID);
}
