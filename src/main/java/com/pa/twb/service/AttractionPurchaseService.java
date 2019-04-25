package com.pa.twb.service;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.AttractionPurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AttractionPurchase.
 */
@Service
@Transactional
public class AttractionPurchaseService {

    private final Logger log = LoggerFactory.getLogger(AttractionPurchaseService.class);

    private final AttractionPurchaseRepository attractionPurchaseRepository;

    public AttractionPurchaseService(AttractionPurchaseRepository attractionPurchaseRepository) {
        this.attractionPurchaseRepository = attractionPurchaseRepository;
    }

    /**
     * Save a attractionPurchase.
     *
     * @param attractionPurchase the entity to save
     * @return the persisted entity
     */
    public AttractionPurchase save(AttractionPurchase attractionPurchase) {
        log.debug("Request to save AttractionPurchase : {}", attractionPurchase);        return attractionPurchaseRepository.save(attractionPurchase);
    }

    /**
     * Get all the attractionPurchases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AttractionPurchase> findAll(Pageable pageable) {
        log.debug("Request to get all AttractionPurchases");
        return attractionPurchaseRepository.findAll(pageable);
    }

    /**
     * Get all the AttractionPurchase with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<AttractionPurchase> findAllWithEagerRelationships(Pageable pageable) {
        return attractionPurchaseRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one attractionPurchase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AttractionPurchase> findOne(Long id) {
        log.debug("Request to get AttractionPurchase : {}", id);
        return attractionPurchaseRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the attractionPurchase by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AttractionPurchase : {}", id);
        attractionPurchaseRepository.deleteById(id);
    }
}
