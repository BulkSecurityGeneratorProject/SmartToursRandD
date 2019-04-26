
package com.pa.twb.service.ext.processing.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MinutelyData {

    @JsonProperty("time")
    private Long time;
    @JsonProperty("precipIntensity")
    private Long precipIntensity;
    @JsonProperty("precipProbability")
    private Long precipProbability;

    @JsonProperty("time")
    public Long getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Long time) {
        this.time = time;
    }

    @JsonProperty("precipIntensity")
    public Long getPrecipIntensity() {
        return precipIntensity;
    }

    @JsonProperty("precipIntensity")
    public void setPrecipIntensity(Long precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    @JsonProperty("precipProbability")
    public Long getPrecipProbability() {
        return precipProbability;
    }

    @JsonProperty("precipProbability")
    public void setPrecipProbability(Long precipProbability) {
        this.precipProbability = precipProbability;
    }

}
