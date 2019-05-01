package com.pa.twb.service.ext.dto.attractionpurchase;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@SuppressWarnings("unused")
public class GetAttractionPurchaseDTO {
    @Min(1L)
    @NotNull
    private Long id;

    private String traveling;

    private String activity;

    private Double userDistance;

    private Double userLatitude;

    private Double userLongitude;

    private Instant createdAt;

    private Instant actionTakenAt;

    private Boolean actionTaken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getActionTakenAt() {
        return actionTakenAt;
    }

    public void setActionTakenAt(Instant actionTakenAt) {
        this.actionTakenAt = actionTakenAt;
    }

    public Boolean getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }
}
