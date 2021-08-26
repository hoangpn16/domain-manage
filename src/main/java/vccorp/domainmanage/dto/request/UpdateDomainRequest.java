package vccorp.domainmanage.dto.request;

import vccorp.domainmanage.enumerates.DomainType;
import vccorp.domainmanage.enumerates.Logtype;
import vccorp.domainmanage.enumerates.Status;

public class UpdateDomainRequest {
    private String domainName;
    private Logtype logType;
    private DomainType domainType;
    private Status status;
}
