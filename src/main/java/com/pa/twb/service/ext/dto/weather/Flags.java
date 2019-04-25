
package com.pa.twb.service.ext.dto.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
