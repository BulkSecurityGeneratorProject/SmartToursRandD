package com.pa.twb.web.rest.errors.ext;

import com.pa.twb.web.rest.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@SuppressWarnings("unused")
public class AttractionPurchaseNotFoundException extends AbstractThrowableProblem {
    public AttractionPurchaseNotFoundException() {
        super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, "AttractionPurchase not found", Status.NOT_FOUND);
    }
}
