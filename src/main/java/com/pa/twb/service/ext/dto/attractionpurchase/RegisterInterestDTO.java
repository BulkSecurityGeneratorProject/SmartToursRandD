package com.pa.twb.service.ext.dto.attractionpurchase;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class RegisterInterestDTO {

    @NotNull
    @Positive
    private Long attractionId;

    @NotNull
    @NotEmpty
    private String traveling;

    @NotNull
    @NotEmpty
    private String activity;

    @NotNull
    @Positive
    private Double userDistance;

    @NotNull
    private Double userLatitude;

    @NotNull
    private Double userLongitude;

    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public String getTraveling() {
        return traveling;
    }

    public void setTraveling(String traveling) {
        this.traveling = traveling;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Double getUserDistance() {
        return userDistance;
    }

    public void setUserDistance(Double userDistance) {
        this.userDistance = userDistance;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }
}
