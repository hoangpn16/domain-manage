package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewGroupDomainRequest;
import vccorp.domainmanage.service.GroupDomainInterface;

@RestController
@RequestMapping(value = "/group-domain")
public class GroupDomainController {
    private static final Logger logger = LoggerFactory.getLogger(GroupDomainController.class);

    @Autowired
    private GroupDomainInterface service;

    @PostMapping
    public ResponseEntity addNewGroupDomain(@RequestBody NewGroupDomainRequest request){
        logger.info("Add new group domain with body {}",request);
        return service.addNew(request);
    }

    @DeleteMapping(value = "/{groupDomainId}")
    public ResponseEntity deleteGroupDomain(@PathVariable("groupDomainId") Long groupDomainId){
        logger.info("Delete group domain id {}",groupDomainId);
        return service.delete(groupDomainId);
    }

    @GetMapping(value = "/domain")
    public ResponseEntity getAllByDomainId(@RequestParam("domain_id") Long domainId){
        logger.info("Get all group domain by domain id {}",domainId);
        return service.getAllByDomainId(domainId);
    }

    @GetMapping(value = "/group")
    public ResponseEntity getAllByGroupId(@RequestParam("group_id") Long groupId){
        logger.info("Get all group domain by group id {}",groupId);
        return service.getAllByGroupId(groupId);
    }


}
