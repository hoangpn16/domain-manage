package vccorp.domainmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.ModeEntity;

import java.util.List;


@Repository
public interface ModeRepository extends JpaRepository<ModeEntity, Integer> {
    ModeEntity findByIdAndStatusNot(Long modeId, Status status);

    List<ModeEntity> findAllByStatusNot(Status status);
}
