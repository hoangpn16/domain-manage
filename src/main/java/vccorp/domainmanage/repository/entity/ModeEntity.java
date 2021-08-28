package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mode")
@Getter @Setter
public class ModeEntity extends BaseEntity {
    @Column(name = "mode_name")
    private String modeName;

    @Column(name = "level_priority")
    private String levelPriority;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "modes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<BasisDomainEntity> lsBasisDomains = new ArrayList<>();

}
