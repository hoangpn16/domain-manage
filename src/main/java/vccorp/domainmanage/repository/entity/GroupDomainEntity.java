package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "group_domain")
@Entity
@Getter @Setter
public class GroupDomainEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id")
    private DomainEntity domain;


}
