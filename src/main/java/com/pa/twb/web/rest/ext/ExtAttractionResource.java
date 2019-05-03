package com.pa.twb.web.rest.ext;

import com.pa.twb.service.ext.ExtAttractionService;
import com.pa.twb.service.ext.dto.attraction.*;
import com.pa.twb.web.rest.util.HeaderUtil;
import com.pa.twb.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ext-attraction")
@SuppressWarnings("unused")
public class ExtAttractionResource {
    private static final String ENTITY_NAME = "attraction";

    private final ExtAttractionService extAttractionService;

    public ExtAttractionResource(ExtAttractionService extAttractionService) {
        this.extAttractionService = extAttractionService;
    }

    @PostMapping
    public ResponseEntity<GetAttractionDTO> createAttraction(@Valid @RequestBody CreateAttractionDTO createAttractionDto) {
        GetAttractionDTO result = extAttractionService.create(createAttractionDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    public ResponseEntity<GetAttractionDTO> updateAttraction(@Valid @RequestBody UpdateAttractionDTO updateAttractionDto) {
        GetAttractionDTO result = extAttractionService.update(updateAttractionDto);
        return ResponseEntity.status(HttpStatus.OK)
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAttractionDTO> getAttractionById(@PathVariable("id") Long id) {
        GetAttractionDTO result = extAttractionService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetAttractionDTO>> getAllAttraction(Pageable pageable) {
        Page<GetAttractionDTO> page = extAttractionService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ext-attraction");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<GetAttractionWithDistanceDTO>> getAllAttractionByLocation(Pageable pageable,
                                                                                         @RequestParam Double latitude,
                                                                                         @RequestParam Double longitude) {
        Page<GetAttractionWithDistanceDTO> page = extAttractionService.getAllByLocation(pageable, latitude, longitude);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ext-attraction/by-location");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/by-recommendation")
    public ResponseEntity<List<GetRecommendationDTO>> getAttractionsByRecommendation(@RequestParam Double latitude,
                                                                                     @RequestParam Double longitude) {
        Optional<List<GetRecommendationDTO>> recommendationsOpt = extAttractionService.getAllByRecommendation(latitude, longitude);
        return recommendationsOpt.map(getRecommendationDTOS -> new ResponseEntity<>(getRecommendationDTOS, HttpStatus.OK)).
            orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
