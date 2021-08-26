package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewModeRequest;
import vccorp.domainmanage.dto.request.UpdateModeRequest;
import vccorp.domainmanage.service.ModeInterface;

@RestController
@RequestMapping(value = "/mode")
public class ModeController {
    private static final Logger logger = LoggerFactory.getLogger(ModeController.class);

    @Autowired
    private ModeInterface service;

    @PostMapping
    public ResponseEntity addMode(@RequestBody NewModeRequest request){
        logger.info("Add new mode with body {}",request);
        return service.addMode(request);
    }

    @PutMapping(value = "/{mode_id}")
    public ResponseEntity updateMode(@PathVariable("mode_id") Long modeId,
                                     @RequestBody UpdateModeRequest request){
        logger.info("Update mode id {} with body {}",modeId,request);
        return service.updateMode(modeId,request);
    }

    @DeleteMapping(value = "/{mode_id}")
    public ResponseEntity deleteMode(@PathVariable("mode_id") Long modeId) {
        logger.info("Delete mode id {}",modeId);
        return service.deleteMode(modeId);
    }

    @GetMapping
    public ResponseEntity getAllMode(){
        logger.info("Get all mode");
        return service.getAllMode();
    }
}
