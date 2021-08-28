package vccorp.domainmanage.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewBasisDomainRequest;
import vccorp.domainmanage.service.BasisDomainInterface;

@RestController
@RequestMapping(value ="/basis-domain")
public class BasisDomainController {
    private static final Logger logger = LoggerFactory.getLogger(BasisDomainController.class);
    @Autowired
    private BasisDomainInterface service;

    @PostMapping
    public ResponseEntity addNewBasisDomain(@RequestBody NewBasisDomainRequest request){
        logger.info("Add new basis domain with body {}",request);
        return service.addNewBasisDomain(request);
    }

    @GetMapping
    public ResponseEntity getAllByDomainId(@RequestParam("domain_id") Long domainId){
        logger.info("Get all basis domain by domain id {}", domainId);
        return service.getAllByDomainId(domainId);
    }
}
