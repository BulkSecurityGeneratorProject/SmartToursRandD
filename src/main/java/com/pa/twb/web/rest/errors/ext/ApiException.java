package com.pa.twb.web.rest.errors.ext;

import com.pa.twb.web.rest.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@SuppressWarnings("unused")
public class ApiException extends AbstractThrowableProblem {
    public ApiException() {
        super(ErrorConstants.CONSTRAINT_VIOLATION_TYPE, "Api Error", Status.BAD_REQUEST);
    }
}
