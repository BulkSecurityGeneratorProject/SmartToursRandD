package com.pa.twb.service.ext.dto.attractionpurchase;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class TakeActionDTO {
    @Min(1L)
    @NotNull
    private Long attractionPurchaseId;

    public Long getAttractionPurchaseId() {
        return attractionPurchaseId;
    }

    public void setAttractionPurchaseId(Long attractionPurchaseId) {
        this.attractionPurchaseId = attractionPurchaseId;
    }
}
