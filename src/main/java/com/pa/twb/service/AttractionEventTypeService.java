package com.pa.twb.service;

import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.repository.AttractionEventTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AttractionEventType.
 */
@Service
@Transactional
public class AttractionEventTypeService {

    private final Logger log = LoggerFactory.getLogger(AttractionEventTypeService.class);

    private final AttractionEventTypeRepository attractionEventTypeRepository;

    public AttractionEventTypeService(AttractionEventTypeRepository attractionEventTypeRepository) {
        this.attractionEventTypeRepository = attractionEventTypeRepository;
    }

    /**
     * Save a attractionEventType.
     *
     * @param attractionEventType the entity to save
     * @return the persisted entity
     */
    public AttractionEventType save(AttractionEventType attractionEventType) {
        log.debug("Request to save AttractionEventType : {}", attractionEventType);        return attractionEventTypeRepository.save(attractionEventType);
    }

    /**
     * Get all the attractionEventTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AttractionEventType> findAll(Pageable pageable) {
        log.debug("Request to get all AttractionEventTypes");
        return attractionEventTypeRepository.findAll(pageable);
    }


    /**
     * Get one attractionEventType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AttractionEventType> findOne(Long id) {
        log.debug("Request to get AttractionEventType : {}", id);
        return attractionEventTypeRepository.findById(id);
    }

    /**
     * Delete the attractionEventType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AttractionEventType : {}", id);
        attractionEventTypeRepository.deleteById(id);
    }
}
