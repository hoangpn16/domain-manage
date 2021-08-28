package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.BasisDomainEntity;
import vccorp.domainmanage.repository.entity.BasisEntity;
import vccorp.domainmanage.repository.entity.DomainEntity;

import java.util.List;


@Repository
public interface BasisDomainRepository extends JpaRepository<BasisDomainEntity,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM basis_domain WHERE domain_id = :domainId AND is_live = true")
    List<BasisDomainEntity> findAllByDomainId(@Param("domainId")Long domainId);

    @Query(nativeQuery = true,value = "SELECT * FROM basis_domain WHERE domain_id = :domainId")
    List<BasisDomainEntity> finnAllHistoryByDomainId(@Param("domainId")Long domainId);

    @Query(nativeQuery = true,value = "SELECT * FROM basis_domain WHERE domain_id = :domainId AND basis_id = :basisId")
    BasisDomainEntity findByDomainIdAndBasisId(@Param("domainId") Long domainId,@Param("basisId") Long basisId);

    BasisDomainEntity findOneById(Long id);



}
