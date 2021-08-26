package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewBasisRequest;
import vccorp.domainmanage.dto.request.UpdateBasisRequest;

public interface BasisInterface {
    ResponseEntity addBasis(NewBasisRequest request);

    ResponseEntity updateBasis(Long basisId,UpdateBasisRequest request);

    ResponseEntity deleteBasis(Long basisId);

    ResponseEntity getAllBasis();
}
