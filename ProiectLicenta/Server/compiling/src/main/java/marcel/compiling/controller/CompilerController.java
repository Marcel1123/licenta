package marcel.compiling.controller;

import marcel.compiling.more.ApiPath;
import marcel.compiling.more.DefaultResponse;
import marcel.compiling.service.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ApiPath.COMPILE_CONTROLLER)
public class CompilerController {

    @Autowired
    ICompilerService compilerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity requestCompile(@PathVariable UUID id){
        compilerService.compile(id);
        return DefaultResponse.BAD_REQUEST_RESPONSE;
    }
}
