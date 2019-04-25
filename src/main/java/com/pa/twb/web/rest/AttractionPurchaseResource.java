package com.pa.twb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.service.AttractionPurchaseService;
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
 * REST controller for managing AttractionPurchase.
 */
@RestController
@RequestMapping("/api")
public class AttractionPurchaseResource {

    private final Logger log = LoggerFactory.getLogger(AttractionPurchaseResource.class);

    private static final String ENTITY_NAME = "attractionPurchase";

    private final AttractionPurchaseService attractionPurchaseService;

    public AttractionPurchaseResource(AttractionPurchaseService attractionPurchaseService) {
        this.attractionPurchaseService = attractionPurchaseService;
    }

    /**
     * POST  /attraction-purchases : Create a new attractionPurchase.
     *
     * @param attractionPurchase the attractionPurchase to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attractionPurchase, or with status 400 (Bad Request) if the attractionPurchase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attraction-purchases")
    @Timed
    public ResponseEntity<AttractionPurchase> createAttractionPurchase(@RequestBody AttractionPurchase attractionPurchase) throws URISyntaxException {
        log.debug("REST request to save AttractionPurchase : {}", attractionPurchase);
        if (attractionPurchase.getId() != null) {
            throw new BadRequestAlertException("A new attractionPurchase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttractionPurchase result = attractionPurchaseService.save(attractionPurchase);
        return ResponseEntity.created(new URI("/api/attraction-purchases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attraction-purchases : Updates an existing attractionPurchase.
     *
     * @param attractionPurchase the attractionPurchase to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attractionPurchase,
     * or with status 400 (Bad Request) if the attractionPurchase is not valid,
     * or with status 500 (Internal Server Error) if the attractionPurchase couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attraction-purchases")
    @Timed
    public ResponseEntity<AttractionPurchase> updateAttractionPurchase(@RequestBody AttractionPurchase attractionPurchase) throws URISyntaxException {
        log.debug("REST request to update AttractionPurchase : {}", attractionPurchase);
        if (attractionPurchase.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttractionPurchase result = attractionPurchaseService.save(attractionPurchase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attractionPurchase.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attraction-purchases : get all the attractionPurchases.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of attractionPurchases in body
     */
    @GetMapping("/attraction-purchases")
    @Timed
    public ResponseEntity<List<AttractionPurchase>> getAllAttractionPurchases(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of AttractionPurchases");
        Page<AttractionPurchase> page;
        if (eagerload) {
            page = attractionPurchaseService.findAllWithEagerRelationships(pageable);
        } else {
            page = attractionPurchaseService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/attraction-purchases?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /attraction-purchases/:id : get the "id" attractionPurchase.
     *
     * @param id the id of the attractionPurchase to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attractionPurchase, or with status 404 (Not Found)
     */
    @GetMapping("/attraction-purchases/{id}")
    @Timed
    public ResponseEntity<AttractionPurchase> getAttractionPurchase(@PathVariable Long id) {
        log.debug("REST request to get AttractionPurchase : {}", id);
        Optional<AttractionPurchase> attractionPurchase = attractionPurchaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attractionPurchase);
    }

    /**
     * DELETE  /attraction-purchases/:id : delete the "id" attractionPurchase.
     *
     * @param id the id of the attractionPurchase to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attraction-purchases/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttractionPurchase(@PathVariable Long id) {
        log.debug("REST request to delete AttractionPurchase : {}", id);
        attractionPurchaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
