package com.pa.twb.web.rest.ext;

import com.pa.twb.service.ext.ExtAttractionPurchaseService;
import com.pa.twb.service.ext.dto.attractionpurchase.GetAttractionPurchaseDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.RegisterInterestDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.TakeActionDTO;
import com.pa.twb.service.ext.processing.MachineLearningTrainerService;
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

@RestController
@RequestMapping("/api/ext-attraction-purchase")
@SuppressWarnings("unused")
public class ExtAttractionPurchaseResource {
    private static final String ENTITY_NAME = "attractionPurchase";

    private final ExtAttractionPurchaseService extAttractionPurchaseService;

    private final MachineLearningTrainerService machineLearningTrainerService;


    public ExtAttractionPurchaseResource(ExtAttractionPurchaseService extAttractionPurchaseService,
                                         MachineLearningTrainerService machineLearningTrainerService) {
        this.extAttractionPurchaseService = extAttractionPurchaseService;
        this.machineLearningTrainerService = machineLearningTrainerService;
    }

    @PostMapping("/interest")
    public ResponseEntity<GetAttractionPurchaseDTO> takeInterest(@Valid @RequestBody RegisterInterestDTO registerInterestDTO) {
        GetAttractionPurchaseDTO result = extAttractionPurchaseService.create(registerInterestDTO);
        machineLearningTrainerService.train();
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/take-action")
    public ResponseEntity<GetAttractionPurchaseDTO> takeAction(@Valid @RequestBody TakeActionDTO takeActionDTO) {
        GetAttractionPurchaseDTO result = extAttractionPurchaseService.takeAction(takeActionDTO);
        machineLearningTrainerService.train();
        return ResponseEntity.status(HttpStatus.OK)
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAttractionPurchaseDTO> getAttractionPurchaseById(@PathVariable("id") Long id) {
        GetAttractionPurchaseDTO result = extAttractionPurchaseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetAttractionPurchaseDTO>> getAllAttractionPurchase(Pageable pageable) {
        Page<GetAttractionPurchaseDTO> page = extAttractionPurchaseService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ext-attraction-purchase");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
