package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.dto.response.BasisResponse;
import vccorp.domainmanage.dto.response.GroupResponse;
import vccorp.domainmanage.dto.response.ModeResponse;
import vccorp.domainmanage.enumerates.DomainType;
import vccorp.domainmanage.enumerates.Logtype;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.BasisEntity;
import vccorp.domainmanage.repository.entity.ModeEntity;

import java.util.List;

@Getter @Setter
public class NewDomainRequest {
    private String domainName;
    private Logtype logType;
    private DomainType domainType;
    private Status status;
    private List<Long> listBasisId;
    private Long modeId;
    private List<Long> listGroupId;
}
