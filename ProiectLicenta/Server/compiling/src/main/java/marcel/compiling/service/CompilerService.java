package marcel.compiling.service;

import marcel.compiling.entity.SubVersionEntity;
import marcel.compiling.repository.CompilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Component
@Service
public class CompilerService implements ICompilerService {
    @Autowired
    private CompilerRepository compilerRepository;


    @Override
    public void compile(UUID id) {
        compilerRepository.compile(id);
    }
}
