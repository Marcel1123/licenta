package marcel.compiling.service;

import marcel.compiling.entity.SubVersionEntity;
import marcel.compiling.exceptions.CompilerException;
import marcel.compiling.exceptions.InvalidObjectsException;
import marcel.compiling.exceptions.VersionAlreadyCompiledException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ICompilerService {
    void compile(UUID id) throws IOException, CompilerException, InvalidObjectsException, VersionAlreadyCompiledException;
}
