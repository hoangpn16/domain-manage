package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.BasisEntity;

import java.util.List;

@Repository
public interface BasisRepository extends JpaRepository<BasisEntity,Integer> {
    BasisEntity findByIdAndStatusNot(Long id, Status status);

    List<BasisEntity> findAllByStatusNot(Status status);
}
