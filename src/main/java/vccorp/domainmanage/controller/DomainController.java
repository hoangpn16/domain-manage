package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewDomainRequest;
import vccorp.domainmanage.dto.request.UpdateDomainRequest;
import vccorp.domainmanage.service.DomainInterface;

@RestController
@RequestMapping(value="/domain")
public class DomainController {
    private static final Logger logger = LoggerFactory.getLogger(DomainController.class);

    @Autowired
    private DomainInterface service;

    @PostMapping
    public ResponseEntity addDomain(@RequestBody NewDomainRequest request){
        logger.info("Add new domain with body {}",request);
        return service.addDomain(request);
    }

    @PutMapping(value = "/{domain_id}")
    public ResponseEntity updateDomain(@PathVariable("domain_id") Long domainId,
                                       @RequestBody UpdateDomainRequest request){
        logger.info("Update domain id {} with body {}",domainId,request);
        return service.updateDomain(domainId,request);
    }

    @DeleteMapping(value = "/{domain_id}")
    public ResponseEntity deleteDomain(@PathVariable("domain_id") Long domainId){
        logger.info("Delete domain id {}",domainId);
        return service.deleteDomain(domainId);
    }

    @GetMapping
    public ResponseEntity getAllDomains(){
        logger.info("Get all domains");
        return service.getAllDomains();
    }
}
