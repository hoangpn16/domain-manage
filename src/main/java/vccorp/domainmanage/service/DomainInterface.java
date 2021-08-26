package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewDomainRequest;

public interface DomainInterface {
    ResponseEntity addDomain(NewDomainRequest request);
}
