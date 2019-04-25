package com.pa.twb.web.rest.errors.ext;

import com.pa.twb.web.rest.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@SuppressWarnings("unused")
public class AttractionGroupTypeNotFoundException extends AbstractThrowableProblem {
    public AttractionGroupTypeNotFoundException() {
        super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, "AttractionGroupType not found", Status.NOT_FOUND);
    }
}
