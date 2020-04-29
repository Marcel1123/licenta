package marcel.compiling.service;

import marcel.compiling.entity.SubVersionEntity;

import java.util.List;
import java.util.UUID;

public interface ICompilerService {
    List<SubVersionEntity> getVersionForCompile(UUID subversionID);
}
