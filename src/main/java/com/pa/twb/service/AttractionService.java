package com.pa.twb.service;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.AttractionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Attraction.
 */
@Service
@Transactional
public class AttractionService {

    private final Logger log = LoggerFactory.getLogger(AttractionService.class);

    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    /**
     * Save a attraction.
     *
     * @param attraction the entity to save
     * @return the persisted entity
     */
    public Attraction save(Attraction attraction) {
        log.debug("Request to save Attraction : {}", attraction);        return attractionRepository.save(attraction);
    }

    /**
     * Get all the attractions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Attraction> findAll(Pageable pageable) {
        log.debug("Request to get all Attractions");
        return attractionRepository.findAll(pageable);
    }

    /**
     * Get all the Attraction with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Attraction> findAllWithEagerRelationships(Pageable pageable) {
        return attractionRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one attraction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Attraction> findOne(Long id) {
        log.debug("Request to get Attraction : {}", id);
        return attractionRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the attraction by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Attraction : {}", id);
        attractionRepository.deleteById(id);
    }
}
