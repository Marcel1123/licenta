package marcel.versionmanagement.controllers;

import com.google.gson.Gson;
import marcel.versionmanagement.entities.SubVersionContentEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.models.VersionModel;
import marcel.versionmanagement.sevices.versions.IVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    IVersionService iVersionService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final ResponseEntity BAD_REQUEST_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addVersion(@RequestBody VersionModel versionModel){
        if(versionModel.getIsFinal().equals("false")){
            String result = iVersionService.addVersion(versionModel);
            if(result == null || result.isEmpty() || UUID.fromString(result).equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))){
                return VersionController.BAD_REQUEST_RESPONSE;
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
        } else if(versionModel.getIsFinal().equals("true")) {
            String result = iVersionService.addFinalVersion(versionModel);
            if(result == null || result.isEmpty() || UUID.fromString(result).equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))){
                return VersionController.BAD_REQUEST_RESPONSE;
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
        } else {
            return VersionController.BAD_REQUEST_RESPONSE;
        }
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllProjectVersion(@PathVariable UUID id){
        Gson g = new Gson();
        logger.info("Start methodGet");
        List<SubVersionEntity> result = iVersionService.getProject(id.toString());
        return ResponseEntity.status(HttpStatus.OK).body(g.toJson(result.toArray(new SubVersionEntity[0]), SubVersionEntity[].class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id){
        List<SubVersionContentEntity> s = iVersionService.getById(id);
        if(s == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(s.toArray(new SubVersionContentEntity[0]));
    }
}
