package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "basis_domain_mode")
@Entity
@Getter @Setter
public class BasisDomainModeEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basis_domain_id")
    private BasisDomainEntity basisDomain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mode_id")
    private ModeEntity mode;
}
