package com.pa.twb.service.ext.processing.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DarkSkyWeatherDTO {

    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("currently")
    private Currently currently;
    @JsonProperty("minutely")
    private Minutely minutely;
    @JsonProperty("hourly")
    private Hourly hourly;
    @JsonProperty("daily")
    private Daily daily;
    @JsonProperty("flags")
    private Flags flags;
    @JsonProperty("offset")
    private Long offset;

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("currently")
    public Currently getCurrently() {
        return currently;
    }

    @JsonProperty("currently")
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    @JsonProperty("minutely")
    public Minutely getMinutely() {
        return minutely;
    }

    @JsonProperty("minutely")
    public void setMinutely(Minutely minutely) {
        this.minutely = minutely;
    }

    @JsonProperty("hourly")
    public Hourly getHourly() {
        return hourly;
    }

    @JsonProperty("hourly")
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    @JsonProperty("daily")
    public Daily getDaily() {
        return daily;
    }

    @JsonProperty("daily")
    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    @JsonProperty("flags")
    public Flags getFlags() {
        return flags;
    }

    @JsonProperty("flags")
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    @JsonProperty("offset")
    public Long getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Long offset) {
        this.offset = offset;
    }

}
