package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.BasisDomainModeEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ModeResponse{
    private Long id;
    private String modeName;
    private String levelPriority;
    private Status status;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    List<BasisDomainModeEntity> listBasisDomainModeEntity = new ArrayList<>();
}
