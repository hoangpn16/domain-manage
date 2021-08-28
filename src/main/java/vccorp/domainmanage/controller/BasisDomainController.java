package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewBasisDomainRequest;
import vccorp.domainmanage.dto.request.UpdateBasisDomainRequest;
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

    @PutMapping(value = "/{basisDomainId}")
    public ResponseEntity updateBasisDomain(@PathVariable("basisDomainId") Long basisDomainId,
                                            @RequestBody UpdateBasisDomainRequest request){
        logger.info("Update basis domain id {} with body {}",basisDomainId,request);
        return service.updateBasisDomain(basisDomainId,request);
    }
    @DeleteMapping(value = "/{basisDomainId}")
    public ResponseEntity deleteBasisDomain(@PathVariable("basisDomainId") Long basisDomainId){
        logger.info("Delete basis domain id {} with body {}",basisDomainId);
        return service.deleteBasisDomain(basisDomainId);
    }

    @GetMapping(value = "/history")
    public ResponseEntity getAllHistoryMode(@RequestParam("domain_id") Long domainId){
        logger.info("Get all history run mode of domain id {}",domainId);
        return service.getAllHistoryMode(domainId);
    }

}
