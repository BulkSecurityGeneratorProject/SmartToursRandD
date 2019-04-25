package com.pa.twb.web.rest.ext;

import com.pa.twb.service.ext.ExtAttractionPurchaseService;
import com.pa.twb.service.ext.dto.attractionpurchase.CreateAttractionPurchaseDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.GetAttractionPurchaseDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.UpdateAttractionPurchaseDTO;
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

    public ExtAttractionPurchaseResource(ExtAttractionPurchaseService extAttractionPurchaseService) {
        this.extAttractionPurchaseService = extAttractionPurchaseService;
    }

    @PostMapping
    public ResponseEntity<GetAttractionPurchaseDTO> createAttractionPurchase(@Valid @RequestBody CreateAttractionPurchaseDTO createAttractionPurchaseDto) {
        GetAttractionPurchaseDTO result = extAttractionPurchaseService.create(createAttractionPurchaseDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    public ResponseEntity<GetAttractionPurchaseDTO> updateAttractionPurchase(@Valid @RequestBody UpdateAttractionPurchaseDTO updateAttractionPurchaseDto) {
        GetAttractionPurchaseDTO result = extAttractionPurchaseService.update(updateAttractionPurchaseDto);
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
