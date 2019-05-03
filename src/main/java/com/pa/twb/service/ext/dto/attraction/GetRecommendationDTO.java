package com.pa.twb.service.ext.dto.attraction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetRecommendationDTO {

    @JsonProperty("index")
    private Long index;
    @JsonProperty("itemId")
    private Long itemId;
    @JsonProperty("distance")
    private Double distance;
    @JsonProperty("actionTaken")
    private Boolean actionTaken;
    @JsonProperty("probability")
    private Double probability;
    @JsonProperty("margin")
    private Double margin;

    private GetAttractionWithDistanceDTO attraction;

    @JsonProperty("index")
    public Long getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(Long index) {
        this.index = index;
    }

    @JsonProperty("itemId")
    public Long getItemId() {
        return itemId;
    }

    @JsonProperty("itemId")
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("distance")
    public Double getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @JsonProperty("actionTaken")
    public Boolean getActionTaken() {
        return actionTaken;
    }

    @JsonProperty("actionTaken")
    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    @JsonProperty("probability")
    public Double getProbability() {
        return probability;
    }

    @JsonProperty("probability")
    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @JsonProperty("margin")
    public Double getMargin() {
        return margin;
    }

    @JsonProperty("margin")
    public void setMargin(Double margin) {
        this.margin = margin;
    }


    public GetAttractionWithDistanceDTO getAttraction() {
        return attraction;
    }

    public void setAttraction(GetAttractionWithDistanceDTO attraction) {
        this.attraction = attraction;
    }

    @Override
    public String toString() {
        return "GetRecommendationDTO{" +
            "index=" + index +
            ", itemId=" + itemId +
            ", distance=" + distance +
            ", actionTaken=" + actionTaken +
            ", probability=" + probability +
            ", margin=" + margin +
            ", attraction=" + attraction +
            '}';
    }
}
