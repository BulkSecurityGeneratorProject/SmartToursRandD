package com.pa.twb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.service.AttractionEventTypeService;
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
 * REST controller for managing AttractionEventType.
 */
@RestController
@RequestMapping("/api")
public class AttractionEventTypeResource {

    private final Logger log = LoggerFactory.getLogger(AttractionEventTypeResource.class);

    private static final String ENTITY_NAME = "attractionEventType";

    private final AttractionEventTypeService attractionEventTypeService;

    public AttractionEventTypeResource(AttractionEventTypeService attractionEventTypeService) {
        this.attractionEventTypeService = attractionEventTypeService;
    }

    /**
     * POST  /attraction-event-types : Create a new attractionEventType.
     *
     * @param attractionEventType the attractionEventType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attractionEventType, or with status 400 (Bad Request) if the attractionEventType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attraction-event-types")
    @Timed
    public ResponseEntity<AttractionEventType> createAttractionEventType(@RequestBody AttractionEventType attractionEventType) throws URISyntaxException {
        log.debug("REST request to save AttractionEventType : {}", attractionEventType);
        if (attractionEventType.getId() != null) {
            throw new BadRequestAlertException("A new attractionEventType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttractionEventType result = attractionEventTypeService.save(attractionEventType);
        return ResponseEntity.created(new URI("/api/attraction-event-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attraction-event-types : Updates an existing attractionEventType.
     *
     * @param attractionEventType the attractionEventType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attractionEventType,
     * or with status 400 (Bad Request) if the attractionEventType is not valid,
     * or with status 500 (Internal Server Error) if the attractionEventType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attraction-event-types")
    @Timed
    public ResponseEntity<AttractionEventType> updateAttractionEventType(@RequestBody AttractionEventType attractionEventType) throws URISyntaxException {
        log.debug("REST request to update AttractionEventType : {}", attractionEventType);
        if (attractionEventType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttractionEventType result = attractionEventTypeService.save(attractionEventType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attractionEventType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attraction-event-types : get all the attractionEventTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of attractionEventTypes in body
     */
    @GetMapping("/attraction-event-types")
    @Timed
    public ResponseEntity<List<AttractionEventType>> getAllAttractionEventTypes(Pageable pageable) {
        log.debug("REST request to get a page of AttractionEventTypes");
        Page<AttractionEventType> page = attractionEventTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attraction-event-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /attraction-event-types/:id : get the "id" attractionEventType.
     *
     * @param id the id of the attractionEventType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attractionEventType, or with status 404 (Not Found)
     */
    @GetMapping("/attraction-event-types/{id}")
    @Timed
    public ResponseEntity<AttractionEventType> getAttractionEventType(@PathVariable Long id) {
        log.debug("REST request to get AttractionEventType : {}", id);
        Optional<AttractionEventType> attractionEventType = attractionEventTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attractionEventType);
    }

    /**
     * DELETE  /attraction-event-types/:id : delete the "id" attractionEventType.
     *
     * @param id the id of the attractionEventType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attraction-event-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttractionEventType(@PathVariable Long id) {
        log.debug("REST request to delete AttractionEventType : {}", id);
        attractionEventTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
