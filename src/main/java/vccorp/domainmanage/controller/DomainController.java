package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vccorp.domainmanage.dto.request.NewDomainRequest;
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
}
