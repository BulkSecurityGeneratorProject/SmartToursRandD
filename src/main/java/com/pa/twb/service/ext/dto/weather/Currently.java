package com.pa.twb.service.ext.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Currently {

    @JsonProperty("time")
    private Long time;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("nearestStormDistance")
    private Long nearestStormDistance;
    @JsonProperty("nearestStormBearing")
    private Long nearestStormBearing;
    @JsonProperty("precipIntensity")
    private Long precipIntensity;
    @JsonProperty("precipProbability")
    private Long precipProbability;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("apparentTemperature")
    private Double apparentTemperature;
    @JsonProperty("dewPoint")
    private Double dewPoint;
    @JsonProperty("humidity")
    private Double humidity;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("windSpeed")
    private Double windSpeed;
    @JsonProperty("windGust")
    private Double windGust;
    @JsonProperty("windBearing")
    private Long windBearing;
    @JsonProperty("cloudCover")
    private Double cloudCover;
    @JsonProperty("uvIndex")
    private Long uvIndex;
    @JsonProperty("visibility")
    private Double visibility;
    @JsonProperty("ozone")
    private Double ozone;

    @JsonProperty("time")
    public Long getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Long time) {
        this.time = time;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("nearestStormDistance")
    public Long getNearestStormDistance() {
        return nearestStormDistance;
    }

    @JsonProperty("nearestStormDistance")
    public void setNearestStormDistance(Long nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
    }

    @JsonProperty("nearestStormBearing")
    public Long getNearestStormBearing() {
        return nearestStormBearing;
    }

    @JsonProperty("nearestStormBearing")
    public void setNearestStormBearing(Long nearestStormBearing) {
        this.nearestStormBearing = nearestStormBearing;
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

    @JsonProperty("temperature")
    public Double getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("apparentTemperature")
    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    @JsonProperty("apparentTemperature")
    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    @JsonProperty("dewPoint")
    public Double getDewPoint() {
        return dewPoint;
    }

    @JsonProperty("dewPoint")
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    @JsonProperty("humidity")
    public Double getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("pressure")
    public Double getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("windSpeed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("windSpeed")
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonProperty("windGust")
    public Double getWindGust() {
        return windGust;
    }

    @JsonProperty("windGust")
    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    @JsonProperty("windBearing")
    public Long getWindBearing() {
        return windBearing;
    }

    @JsonProperty("windBearing")
    public void setWindBearing(Long windBearing) {
        this.windBearing = windBearing;
    }

    @JsonProperty("cloudCover")
    public Double getCloudCover() {
        return cloudCover;
    }

    @JsonProperty("cloudCover")
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    @JsonProperty("uvIndex")
    public Long getUvIndex() {
        return uvIndex;
    }

    @JsonProperty("uvIndex")
    public void setUvIndex(Long uvIndex) {
        this.uvIndex = uvIndex;
    }

    @JsonProperty("visibility")
    public Double getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("ozone")
    public Double getOzone() {
        return ozone;
    }

    @JsonProperty("ozone")
    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

}
