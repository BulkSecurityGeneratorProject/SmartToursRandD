package com.pa.twb.service.ext.dto.attraction;

public class RequestRecommendationDTO {

    private Long itemId;

    private Double distance;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
