package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.DomainType;
import vccorp.domainmanage.enumerates.Logtype;
import vccorp.domainmanage.enumerates.Status;

import java.sql.Timestamp;

@Getter @Setter
public class DomainResponse {
    private Long id;
    private String domainName;
    private Logtype logType;
    private DomainType domainType;
    private Status status;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
