package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewBasisDomainRequest {
    private Long basisId;
    private Long domainId;
    private Long modeId;
}
