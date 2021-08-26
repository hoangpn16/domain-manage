package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.BasisDomainEntity;

@Repository
public interface BasisDomainRepository extends JpaRepository<BasisDomainEntity,Integer> {
}
