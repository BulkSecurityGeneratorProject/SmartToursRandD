
package com.pa.twb.service.ext.processing.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Flags {

    @JsonProperty("sources")
    private List<String> sources = new ArrayList<String>();
    @JsonProperty("nearest-station")
    private Double nearestStation;
    @JsonProperty("units")
    private String units;

    @JsonProperty("sources")
    public List<String> getSources() {
        return sources;
    }

    @JsonProperty("sources")
    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    @JsonProperty("nearest-station")
    public Double getNearestStation() {
        return nearestStation;
    }

    @JsonProperty("nearest-station")
    public void setNearestStation(Double nearestStation) {
        this.nearestStation = nearestStation;
    }

    @JsonProperty("units")
    public String getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(String units) {
        this.units = units;
    }

}
