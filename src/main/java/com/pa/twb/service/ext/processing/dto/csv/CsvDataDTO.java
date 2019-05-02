package com.pa.twb.service.ext.processing.dto.csv;

public class CsvDataDTO {

    private Double userDistance;
    private Boolean actionTaken;

    public Double getUserDistance() {
        return userDistance;
    }

    public void setUserDistance(Double userDistance) {
        this.userDistance = userDistance;
    }

    public Boolean getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }
}
