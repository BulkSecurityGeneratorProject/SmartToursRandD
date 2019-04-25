package com.pa.twb.service.ext.dto.attraction;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class UpdateAttractionDTO {
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
