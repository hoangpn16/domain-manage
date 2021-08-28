package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewBasisDomainRequest;
import vccorp.domainmanage.dto.request.UpdateBasisDomainRepository;

public interface BasisDomainInterface {
    ResponseEntity addNewBasisDomain(NewBasisDomainRequest request);

    ResponseEntity updateBasisDomain(Long basisDomainId,UpdateBasisDomainRepository request);

    ResponseEntity deleteBasisDomain(Long basisDomainId);

    ResponseEntity getAllByDomainId(Long domainId);
}
