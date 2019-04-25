package com.pa.twb.service.ext.dto.attractiongrouptype;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class UpdateAttractionGroupTypeDTO {
    @Min(1L)
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
