package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.GroupEntity;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Integer> {
    GroupEntity findByIdAndStatusNot(Long id, Status status);

    List<GroupEntity> findAllByStatusNot(Status status);
}
