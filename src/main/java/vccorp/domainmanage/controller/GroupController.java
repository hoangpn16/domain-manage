package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewGroupRequest;
import vccorp.domainmanage.dto.request.UpdateGroupRequest;
import vccorp.domainmanage.dto.response.GroupResponse;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.service.GroupInterface;


@RestController
@RequestMapping(value ="/group")
public class GroupController {
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private GroupInterface service;


    @PostMapping
    public ResponseEntity addNewGroup(@RequestBody NewGroupRequest request){
        logger.info("Add new group with body {}",request);
        return service.addGroup(request);
    }

    @PutMapping(value = "/{group_id}")
    public ResponseEntity updateGroup(@PathVariable("group_id") Long groupId,
                                      @RequestBody UpdateGroupRequest request){
        logger.info("Update group id {} with body {}",groupId,request);
        return service.updateGroup(groupId,request);
    }

    @GetMapping
    public ResponseEntity getAllGroup(){
        logger.info("Get all group");
        return service.getAllGroup();
    }

    @DeleteMapping(value = "/{group_id}")
    public ResponseEntity deleteGroup(@PathVariable("group_id") Long groupId){
        logger.info("Delete group id {}",groupId);
        return service.deleteGroup(groupId);
    }
}
