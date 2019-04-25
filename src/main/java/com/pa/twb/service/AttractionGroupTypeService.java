package com.pa.twb.service;

import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.repository.AttractionGroupTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AttractionGroupType.
 */
@Service
@Transactional
public class AttractionGroupTypeService {

    private final Logger log = LoggerFactory.getLogger(AttractionGroupTypeService.class);

    private final AttractionGroupTypeRepository attractionGroupTypeRepository;

    public AttractionGroupTypeService(AttractionGroupTypeRepository attractionGroupTypeRepository) {
        this.attractionGroupTypeRepository = attractionGroupTypeRepository;
    }

    /**
     * Save a attractionGroupType.
     *
     * @param attractionGroupType the entity to save
     * @return the persisted entity
     */
    public AttractionGroupType save(AttractionGroupType attractionGroupType) {
        log.debug("Request to save AttractionGroupType : {}", attractionGroupType);        return attractionGroupTypeRepository.save(attractionGroupType);
    }

    /**
     * Get all the attractionGroupTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AttractionGroupType> findAll(Pageable pageable) {
        log.debug("Request to get all AttractionGroupTypes");
        return attractionGroupTypeRepository.findAll(pageable);
    }


    /**
     * Get one attractionGroupType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AttractionGroupType> findOne(Long id) {
        log.debug("Request to get AttractionGroupType : {}", id);
        return attractionGroupTypeRepository.findById(id);
    }

    /**
     * Delete the attractionGroupType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AttractionGroupType : {}", id);
        attractionGroupTypeRepository.deleteById(id);
    }
}
