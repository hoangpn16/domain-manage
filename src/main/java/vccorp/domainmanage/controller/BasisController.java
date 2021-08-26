package vccorp.domainmanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vccorp.domainmanage.dto.request.NewBasisRequest;
import vccorp.domainmanage.dto.request.UpdateBasisRequest;
import vccorp.domainmanage.service.BasisInterface;

@RestController
@RequestMapping(value = "/basis")
public class BasisController {
    private static final Logger logger = LoggerFactory.getLogger(BasisController.class);

    @Autowired
    private BasisInterface service;

    @PostMapping
    public ResponseEntity addBasis(@RequestBody NewBasisRequest request) {
        logger.info("Add new basis with body {}", request);
        return service.addBasis(request);
    }

    @PutMapping(value = "/{basis_id}")
    public ResponseEntity updateBasis(@PathVariable("basis_id") Long basisId,
                                      @RequestBody UpdateBasisRequest request){
        logger.info("Update basis id {} with body {}",basisId,request);
        return service.updateBasis(basisId, request);
    }

    @DeleteMapping(value = "/{basis_id}")
    public ResponseEntity deleteBasis(@PathVariable("basis_id") Long basisId){
        logger.info("Delete basis id {}",basisId);
        return service.deleteBasis(basisId);
    }

    @GetMapping
    public ResponseEntity getAllBasis(){
        logger.info("Get all basis");
        return service.getAllBasis();
    }

}
