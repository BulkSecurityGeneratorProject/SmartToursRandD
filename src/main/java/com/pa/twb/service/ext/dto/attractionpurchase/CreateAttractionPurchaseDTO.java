package com.pa.twb.service.ext.dto.attractionpurchase;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class CreateAttractionPurchaseDTO {

    @NotNull
    @Positive
    private Long attractionId;
}
