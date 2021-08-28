package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GroupDomainResponse {
    private Long id;
    private GroupResponse group;
    private DomainResponse domain;
}
