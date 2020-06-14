package marcel.compiling.controllers;

import marcel.compiling.exceptions.CompilerException;
import marcel.compiling.exceptions.InvalidObjectsException;
import marcel.compiling.exceptions.VersionAlreadyCompiledException;
import marcel.compiling.more.ApiPath;
import marcel.compiling.more.CompilingModel;
import marcel.compiling.service.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(ApiPath.COMPILE_CONTROLLER)
public class CompilerController {

    @Autowired
    ICompilerService compilerService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity requestCompile(@RequestBody CompilingModel id) throws IOException, CompilerException, InvalidObjectsException, VersionAlreadyCompiledException {
        compilerService.compile(UUID.fromString(id.getVersionId()));
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
