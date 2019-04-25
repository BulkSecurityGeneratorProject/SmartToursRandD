package com.pa.twb.web.rest.ext;

import com.pa.twb.service.ext.ExtAttractionGroupTypeService;
import com.pa.twb.service.ext.dto.attractiongrouptype.CreateAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.GetAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.UpdateAttractionGroupTypeDTO;
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
@RequestMapping("/api/ext-attraction-group-type")
@SuppressWarnings("unused")
public class ExtAttractionGroupTypeResource {
    private static final String ENTITY_NAME = "attractionGroupType";

    private final ExtAttractionGroupTypeService extAttractionGroupTypeService;

    public ExtAttractionGroupTypeResource(ExtAttractionGroupTypeService extAttractionGroupTypeService) {
        this.extAttractionGroupTypeService = extAttractionGroupTypeService;
    }

    @PostMapping
    public ResponseEntity<GetAttractionGroupTypeDTO> createAttractionGroupType(@Valid @RequestBody CreateAttractionGroupTypeDTO createAttractionGroupTypeDto) {
        GetAttractionGroupTypeDTO result = extAttractionGroupTypeService.create(createAttractionGroupTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    public ResponseEntity<GetAttractionGroupTypeDTO> updateAttractionGroupType(@Valid @RequestBody UpdateAttractionGroupTypeDTO updateAttractionGroupTypeDto) {
        GetAttractionGroupTypeDTO result = extAttractionGroupTypeService.update(updateAttractionGroupTypeDto);
        return ResponseEntity.status(HttpStatus.OK)
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAttractionGroupTypeDTO> getAttractionGroupTypeById(@PathVariable("id") Long id) {
        GetAttractionGroupTypeDTO result = extAttractionGroupTypeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetAttractionGroupTypeDTO>> getAllAttractionGroupType(Pageable pageable) {
        Page<GetAttractionGroupTypeDTO> page = extAttractionGroupTypeService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ext-attraction-group-type");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
