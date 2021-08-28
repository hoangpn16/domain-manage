package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basis_domain")
@Getter @Setter
public class BasisDomainEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basis_id")
    private BasisEntity basis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id")
    private DomainEntity domains;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mode_id")
    private ModeEntity modes;

    @Column(name = "is_live")
    private Boolean isLive;


}
