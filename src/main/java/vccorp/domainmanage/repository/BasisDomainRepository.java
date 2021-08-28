package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.BasisDomainEntity;

import java.util.List;

@Repository
public interface BasisDomainRepository extends JpaRepository<BasisDomainEntity,Integer> {
    List<BasisDomainEntity> findAllByDomains(Long domainId);

    BasisDomainEntity findByDomainsAndBasis(Long domainId, Long basisId);
}
