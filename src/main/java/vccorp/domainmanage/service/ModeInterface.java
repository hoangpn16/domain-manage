package vccorp.domainmanage.service;

import org.springframework.http.ResponseEntity;
import vccorp.domainmanage.dto.request.NewModeRequest;
import vccorp.domainmanage.dto.request.UpdateModeRequest;

public interface ModeInterface {
    ResponseEntity addMode(NewModeRequest request);

    ResponseEntity updateMode(Long modeId, UpdateModeRequest request);

    ResponseEntity deleteMode(Long modeId);

    ResponseEntity getAllMode();
}
