package com.pa.twb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pa.twb.domain.TrainingLock;
import com.pa.twb.service.TrainingLockService;
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
 * REST controller for managing TrainingLock.
 */
@RestController
@RequestMapping("/api")
public class TrainingLockResource {

    private static final String ENTITY_NAME = "trainingLock";
    private final Logger log = LoggerFactory.getLogger(TrainingLockResource.class);
    private final TrainingLockService trainingLockService;

    public TrainingLockResource(TrainingLockService trainingLockService) {
        this.trainingLockService = trainingLockService;
    }

    /**
     * POST  /training-locks : Create a new trainingLock.
     *
     * @param trainingLock the trainingLock to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trainingLock, or with status 400 (Bad Request) if the trainingLock has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/training-locks")
    @Timed
    public ResponseEntity<TrainingLock> createTrainingLock(@RequestBody TrainingLock trainingLock) throws URISyntaxException {
        log.debug("REST request to save TrainingLock : {}", trainingLock);
        if (trainingLock.getId() != null) {
            throw new BadRequestAlertException("A new trainingLock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrainingLock result = trainingLockService.save(trainingLock);
        return ResponseEntity.created(new URI("/api/training-locks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /training-locks : Updates an existing trainingLock.
     *
     * @param trainingLock the trainingLock to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trainingLock,
     * or with status 400 (Bad Request) if the trainingLock is not valid,
     * or with status 500 (Internal Server Error) if the trainingLock couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/training-locks")
    @Timed
    public ResponseEntity<TrainingLock> updateTrainingLock(@RequestBody TrainingLock trainingLock) throws URISyntaxException {
        log.debug("REST request to update TrainingLock : {}", trainingLock);
        if (trainingLock.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrainingLock result = trainingLockService.save(trainingLock);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trainingLock.getId().toString()))
            .body(result);
    }

    /**
     * GET  /training-locks : get all the trainingLocks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trainingLocks in body
     */
    @GetMapping("/training-locks")
    @Timed
    public ResponseEntity<List<TrainingLock>> getAllTrainingLocks(Pageable pageable) {
        log.debug("REST request to get a page of TrainingLocks");
        Page<TrainingLock> page = trainingLockService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/training-locks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /training-locks/:id : get the "id" trainingLock.
     *
     * @param id the id of the trainingLock to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trainingLock, or with status 404 (Not Found)
     */
    @GetMapping("/training-locks/{id}")
    @Timed
    public ResponseEntity<TrainingLock> getTrainingLock(@PathVariable Long id) {
        log.debug("REST request to get TrainingLock : {}", id);
        Optional<TrainingLock> trainingLock = trainingLockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trainingLock);
    }

    /**
     * DELETE  /training-locks/:id : delete the "id" trainingLock.
     *
     * @param id the id of the trainingLock to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/training-locks/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrainingLock(@PathVariable Long id) {
        log.debug("REST request to delete TrainingLock : {}", id);
        trainingLockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
