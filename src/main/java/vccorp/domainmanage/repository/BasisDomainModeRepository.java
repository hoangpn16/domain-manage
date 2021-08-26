package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.BasisDomainModeEntity;

@Repository
public interface BasisDomainModeRepository extends JpaRepository<BasisDomainModeEntity,Integer> {
}
