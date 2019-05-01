package com.pa.twb.service.ext.processing.dto.location;

public class GetEntityWithLocationDTO {

    private Long id;
    private Double distance;

    public GetEntityWithLocationDTO(Long id, Double distance) {
        this.id = id;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
