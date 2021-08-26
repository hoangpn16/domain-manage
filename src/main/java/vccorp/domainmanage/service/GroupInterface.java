package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewGroupRequest;
import vccorp.domainmanage.dto.request.UpdateGroupRequest;
import vccorp.domainmanage.dto.response.GroupResponse;


public interface GroupInterface {
    ResponseEntity addGroup(NewGroupRequest request);
    ResponseEntity updateGroup(Long groupId,UpdateGroupRequest request);
    ResponseEntity deleteGroup(Long groupId);
    ResponseEntity getAllGroup();

}
