package com.pa.twb.service;

import com.pa.twb.domain.TrainingLock;
import com.pa.twb.repository.TrainingLockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TrainingLock.
 */
@Service
@Transactional
public class TrainingLockService {

    private final Logger log = LoggerFactory.getLogger(TrainingLockService.class);

    private final TrainingLockRepository trainingLockRepository;

    public TrainingLockService(TrainingLockRepository trainingLockRepository) {
        this.trainingLockRepository = trainingLockRepository;
    }

    /**
     * Save a trainingLock.
     *
     * @param trainingLock the entity to save
     * @return the persisted entity
     */
    public TrainingLock save(TrainingLock trainingLock) {
        log.debug("Request to save TrainingLock : {}", trainingLock);
        return trainingLockRepository.save(trainingLock);
    }

    /**
     * Get all the trainingLocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TrainingLock> findAll(Pageable pageable) {
        log.debug("Request to get all TrainingLocks");
        return trainingLockRepository.findAll(pageable);
    }


    /**
     * Get one trainingLock by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TrainingLock> findOne(Long id) {
        log.debug("Request to get TrainingLock : {}", id);
        return trainingLockRepository.findById(id);
    }

    /**
     * Delete the trainingLock by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TrainingLock : {}", id);
        trainingLockRepository.deleteById(id);
    }
}
