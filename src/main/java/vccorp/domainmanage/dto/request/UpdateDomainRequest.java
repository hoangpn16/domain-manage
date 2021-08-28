package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.DomainType;
import vccorp.domainmanage.enumerates.Logtype;
import vccorp.domainmanage.enumerates.Status;

@Getter @Setter
public class UpdateDomainRequest {
    private String domainName;
    private Logtype logType;
    private DomainType domainType;
    private Status status;
}
