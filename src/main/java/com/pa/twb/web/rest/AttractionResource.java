package com.pa.twb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pa.twb.domain.Attraction;
import com.pa.twb.service.AttractionService;
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
 * REST controller for managing Attraction.
 */
@RestController
@RequestMapping("/api")
public class AttractionResource {

    private final Logger log = LoggerFactory.getLogger(AttractionResource.class);

    private static final String ENTITY_NAME = "attraction";

    private final AttractionService attractionService;

    public AttractionResource(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    /**
     * POST  /attractions : Create a new attraction.
     *
     * @param attraction the attraction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attraction, or with status 400 (Bad Request) if the attraction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attractions")
    @Timed
    public ResponseEntity<Attraction> createAttraction(@RequestBody Attraction attraction) throws URISyntaxException {
        log.debug("REST request to save Attraction : {}", attraction);
        if (attraction.getId() != null) {
            throw new BadRequestAlertException("A new attraction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Attraction result = attractionService.save(attraction);
        return ResponseEntity.created(new URI("/api/attractions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attractions : Updates an existing attraction.
     *
     * @param attraction the attraction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attraction,
     * or with status 400 (Bad Request) if the attraction is not valid,
     * or with status 500 (Internal Server Error) if the attraction couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attractions")
    @Timed
    public ResponseEntity<Attraction> updateAttraction(@RequestBody Attraction attraction) throws URISyntaxException {
        log.debug("REST request to update Attraction : {}", attraction);
        if (attraction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Attraction result = attractionService.save(attraction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attraction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attractions : get all the attractions.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of attractions in body
     */
    @GetMapping("/attractions")
    @Timed
    public ResponseEntity<List<Attraction>> getAllAttractions(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Attractions");
        Page<Attraction> page;
        if (eagerload) {
            page = attractionService.findAllWithEagerRelationships(pageable);
        } else {
            page = attractionService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/attractions?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /attractions/:id : get the "id" attraction.
     *
     * @param id the id of the attraction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attraction, or with status 404 (Not Found)
     */
    @GetMapping("/attractions/{id}")
    @Timed
    public ResponseEntity<Attraction> getAttraction(@PathVariable Long id) {
        log.debug("REST request to get Attraction : {}", id);
        Optional<Attraction> attraction = attractionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attraction);
    }

    /**
     * DELETE  /attractions/:id : delete the "id" attraction.
     *
     * @param id the id of the attraction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attractions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        log.debug("REST request to delete Attraction : {}", id);
        attractionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
