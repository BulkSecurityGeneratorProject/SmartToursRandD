package com.pa.twb.web.rest.errors.ext;

import com.pa.twb.web.rest.errors.BadRequestAlertException;

public class NoLocationProvidedException extends BadRequestAlertException {
    public NoLocationProvidedException() {
        super("No Locaion Provided", "WorkOrder", "nolocationprovided");
    }
}
