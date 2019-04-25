package com.pa.twb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.service.AttractionGroupTypeService;
import com.pa.twb.web.rest.errors.BadRequestAlertException;
import com.pa.twb.web.rest.util.HeaderUtil;
import com.pa.twb.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AttractionGroupType.
 */
@RestController
@RequestMapping("/api")
public class AttractionGroupTypeResource {

    private final Logger log = LoggerFactory.getLogger(AttractionGroupTypeResource.class);

    private static final String ENTITY_NAME = "attractionGroupType";

    private final AttractionGroupTypeService attractionGroupTypeService;

    public AttractionGroupTypeResource(AttractionGroupTypeService attractionGroupTypeService) {
        this.attractionGroupTypeService = attractionGroupTypeService;
    }

    /**
     * POST  /attraction-group-types : Create a new attractionGroupType.
     *
     * @param attractionGroupType the attractionGroupType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attractionGroupType, or with status 400 (Bad Request) if the attractionGroupType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attraction-group-types")
    @Timed
    public ResponseEntity<AttractionGroupType> createAttractionGroupType(@RequestBody AttractionGroupType attractionGroupType) throws URISyntaxException {
        log.debug("REST request to save AttractionGroupType : {}", attractionGroupType);
        if (attractionGroupType.getId() != null) {
            throw new BadRequestAlertException("A new attractionGroupType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttractionGroupType result = attractionGroupTypeService.save(attractionGroupType);
        return ResponseEntity.created(new URI("/api/attraction-group-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attraction-group-types : Updates an existing attractionGroupType.
     *
     * @param attractionGroupType the attractionGroupType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attractionGroupType,
     * or with status 400 (Bad Request) if the attractionGroupType is not valid,
     * or with status 500 (Internal Server Error) if the attractionGroupType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attraction-group-types")
    @Timed
    public ResponseEntity<AttractionGroupType> updateAttractionGroupType(@RequestBody AttractionGroupType attractionGroupType) throws URISyntaxException {
        log.debug("REST request to update AttractionGroupType : {}", attractionGroupType);
        if (attractionGroupType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttractionGroupType result = attractionGroupTypeService.save(attractionGroupType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attractionGroupType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attraction-group-types : get all the attractionGroupTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of attractionGroupTypes in body
     */
    @GetMapping("/attraction-group-types")
    @Timed
    public ResponseEntity<List<AttractionGroupType>> getAllAttractionGroupTypes(Pageable pageable) {
        log.debug("REST request to get a page of AttractionGroupTypes");
        Page<AttractionGroupType> page = attractionGroupTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attraction-group-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /attraction-group-types/:id : get the "id" attractionGroupType.
     *
     * @param id the id of the attractionGroupType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attractionGroupType, or with status 404 (Not Found)
     */
    @GetMapping("/attraction-group-types/{id}")
    @Timed
    public ResponseEntity<AttractionGroupType> getAttractionGroupType(@PathVariable Long id) {
        log.debug("REST request to get AttractionGroupType : {}", id);
        Optional<AttractionGroupType> attractionGroupType = attractionGroupTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attractionGroupType);
    }

    /**
     * DELETE  /attraction-group-types/:id : delete the "id" attractionGroupType.
     *
     * @param id the id of the attractionGroupType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attraction-group-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttractionGroupType(@PathVariable Long id) {
        log.debug("REST request to delete AttractionGroupType : {}", id);
        attractionGroupTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
