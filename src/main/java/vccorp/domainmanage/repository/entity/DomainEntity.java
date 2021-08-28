package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.DomainType;
import vccorp.domainmanage.enumerates.Logtype;
import vccorp.domainmanage.enumerates.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "domains")
@Entity
@Getter @Setter
public class DomainEntity extends BaseEntity{
    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "log_type")
    @Enumerated(EnumType.STRING)
    private Logtype logType;

    @Column(name = "domain_type")
    @Enumerated(EnumType.STRING)
    private DomainType domainType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "domain",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<GroupDomainEntity> lstGroupDomains = new ArrayList<>();

    @OneToMany(mappedBy = "domains",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BasisDomainEntity> lstBasisDomains = new ArrayList<>();
}
