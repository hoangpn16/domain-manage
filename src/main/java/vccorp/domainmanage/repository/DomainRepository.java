package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.DomainEntity;

import java.util.List;

@Repository
public interface DomainRepository extends JpaRepository<DomainEntity,Integer> {
    DomainEntity findByIdAndStatusNot(Long domainId, Status status);

    List<DomainEntity> findAllByStatusNot(Status status);
}
