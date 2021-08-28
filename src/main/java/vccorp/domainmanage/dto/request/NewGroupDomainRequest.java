package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class NewGroupDomainRequest {
    private Long groupId;
    private Long domainId;

}
