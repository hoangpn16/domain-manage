package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.GroupDomainEntity;

import java.util.List;

@Repository
public interface GroupDomainRepository extends JpaRepository<GroupDomainEntity,Integer> {
    List<GroupDomainEntity> findAllByDomainId(Long domainId);
}
