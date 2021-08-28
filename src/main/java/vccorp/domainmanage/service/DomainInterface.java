package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewDomainRequest;
import vccorp.domainmanage.dto.request.UpdateDomainRequest;

public interface DomainInterface {
    ResponseEntity addDomain(NewDomainRequest request);

    ResponseEntity updateDomain(Long domainId,UpdateDomainRequest request);

    ResponseEntity deleteDomain(Long domainId);

    ResponseEntity getAllDomains();
}
