package vccorp.domainmanage.repository.entity;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basis")
@Getter @Setter
public class BasisEntity extends BaseEntity{
    @Column(name = "basis_name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "basis",fetch = FetchType.LAZY)
    List<BasisDomainEntity> listBasisDomains = new ArrayList<>();
}
