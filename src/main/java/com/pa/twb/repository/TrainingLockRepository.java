package com.pa.twb.repository;

import com.pa.twb.domain.TrainingLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrainingLock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrainingLockRepository extends JpaRepository<TrainingLock, Long> {

}
