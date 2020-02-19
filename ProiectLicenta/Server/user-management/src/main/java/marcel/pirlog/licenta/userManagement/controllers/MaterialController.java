package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.services.material.IMaterialService;
import marcel.pirlog.licenta.userManagement.utils.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ControllerAdvice
@RequestMapping(RequestPath.MATERIAL_ROUT)
public class MaterialController {

    IMaterialService materialService;

    @Autowired
    public MaterialController(IMaterialService materialService){
        this.materialService = materialService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(materialService.findAll().toArray());
    }

}
