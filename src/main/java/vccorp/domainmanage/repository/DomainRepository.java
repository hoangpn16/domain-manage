package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.DomainEntity;

@Repository
public interface DomainRepository extends JpaRepository<DomainEntity,Integer> {
}
