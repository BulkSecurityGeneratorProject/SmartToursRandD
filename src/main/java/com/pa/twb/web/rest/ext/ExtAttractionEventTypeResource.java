package com.pa.twb.web.rest.ext;

import com.pa.twb.service.ext.ExtAttractionEventTypeService;
import com.pa.twb.service.ext.dto.attractioneventtype.CreateAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.GetAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.UpdateAttractionEventTypeDTO;
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
@RequestMapping("/api/ext-attraction-event-type")
@SuppressWarnings("unused")
public class ExtAttractionEventTypeResource {
    private static final String ENTITY_NAME = "attractionEventType";

    private final ExtAttractionEventTypeService extAttractionEventTypeService;

    public ExtAttractionEventTypeResource(ExtAttractionEventTypeService extAttractionEventTypeService) {
        this.extAttractionEventTypeService = extAttractionEventTypeService;
    }

    @PostMapping
    public ResponseEntity<GetAttractionEventTypeDTO> createAttractionEventType(@Valid @RequestBody CreateAttractionEventTypeDTO createAttractionEventTypeDto) {
        GetAttractionEventTypeDTO result = extAttractionEventTypeService.create(createAttractionEventTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    public ResponseEntity<GetAttractionEventTypeDTO> updateAttractionEventType(@Valid @RequestBody UpdateAttractionEventTypeDTO updateAttractionEventTypeDto) {
        GetAttractionEventTypeDTO result = extAttractionEventTypeService.update(updateAttractionEventTypeDto);
        return ResponseEntity.status(HttpStatus.OK)
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAttractionEventTypeDTO> getAttractionEventTypeById(@PathVariable("id") Long id) {
        GetAttractionEventTypeDTO result = extAttractionEventTypeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetAttractionEventTypeDTO>> getAllAttractionEventType(Pageable pageable) {
        Page<GetAttractionEventTypeDTO> page = extAttractionEventTypeService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ext-attraction-event-type");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
