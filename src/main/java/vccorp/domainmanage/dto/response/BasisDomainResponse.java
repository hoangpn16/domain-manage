package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.repository.entity.BasisEntity;
import vccorp.domainmanage.repository.entity.DomainEntity;
import vccorp.domainmanage.repository.entity.ModeEntity;

@Getter @Setter
public class BasisDomainResponse {
    private DomainEntity domain;
    private BasisEntity basis;
    private ModeEntity mode;
}
