package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "groups")
@Entity
@Getter @Setter
public class GroupEntity extends BaseEntity{
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER)
    private List<GroupDomainEntity> listGroupDomains = new ArrayList<>();
}
