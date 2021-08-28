package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewGroupDomainRequest;

public interface GroupDomainInterface {
    ResponseEntity addNew(NewGroupDomainRequest request);
    ResponseEntity delete(Long groupDomainId);
    ResponseEntity getAllByDomainId(Long domainId);
    ResponseEntity getAllByGroupId(Long groupId);
}
