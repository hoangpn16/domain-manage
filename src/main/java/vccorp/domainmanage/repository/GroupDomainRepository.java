package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.repository.entity.DomainEntity;
import vccorp.domainmanage.repository.entity.GroupDomainEntity;
import vccorp.domainmanage.repository.entity.GroupEntity;

import java.util.List;

@Repository
public interface GroupDomainRepository extends JpaRepository<GroupDomainEntity,Integer> {
    @Query(nativeQuery = true , value = "SELECT * FROM group_domain WHERE domain_id = :domainId")
    List<GroupDomainEntity> findAllByDomainId(@Param("domainId") Long domainId);

    @Query(nativeQuery = true , value = "SELECT * FROM group_domain WHERE group_id = :groupId")
    List<GroupDomainEntity> findAllByGroupId(@Param("groupId") Long groupId);

    @Query(nativeQuery = true,value = "SELECT * FROM group_domain WHERE domain_id = :domainId AND group_id= :groupId")
    GroupDomainEntity findByDomainIdAndGroupId(@Param("domainId") Long domainId,@Param("groupId") Long groupId);

    GroupDomainEntity findById(Long id);
}
