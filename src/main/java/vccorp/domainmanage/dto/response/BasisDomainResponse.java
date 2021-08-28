package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BasisDomainResponse {
    private Long id;
    private DomainResponse domains;
    private BasisResponse basis;
    private ModeResponse modes;
    private Boolean isLive;
}
