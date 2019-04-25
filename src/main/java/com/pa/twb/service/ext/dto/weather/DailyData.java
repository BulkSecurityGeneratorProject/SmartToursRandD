
package com.pa.twb.service.ext.dto.weather;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class DailyData {

    @JsonProperty("time")
    private Long time;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("sunriseTime")
    private Long sunriseTime;
    @JsonProperty("sunsetTime")
    private Long sunsetTime;
    @JsonProperty("moonPhase")
    private Double moonPhase;
    @JsonProperty("precipIntensity")
    private Long precipIntensity;
    @JsonProperty("precipIntensityMax")
    private Long precipIntensityMax;
    @JsonProperty("precipIntensityMaxTime")
    private Long precipIntensityMaxTime;
    @JsonProperty("precipProbability")
    private Long precipProbability;
    @JsonProperty("precipType")
    private String precipType;
    @JsonProperty("temperatureHigh")
    private Double temperatureHigh;
    @JsonProperty("temperatureHighTime")
    private Long temperatureHighTime;
    @JsonProperty("temperatureLow")
    private Double temperatureLow;
    @JsonProperty("temperatureLowTime")
    private Long temperatureLowTime;
    @JsonProperty("apparentTemperatureHigh")
    private Double apparentTemperatureHigh;
    @JsonProperty("apparentTemperatureHighTime")
    private Long apparentTemperatureHighTime;
    @JsonProperty("apparentTemperatureLow")
    private Double apparentTemperatureLow;
    @JsonProperty("apparentTemperatureLowTime")
    private Long apparentTemperatureLowTime;
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
    @JsonProperty("windGustTime")
    private Long windGustTime;
    @JsonProperty("windBearing")
    private Long windBearing;
    @JsonProperty("cloudCover")
    private Double cloudCover;
    @JsonProperty("uvIndex")
    private Long uvIndex;
    @JsonProperty("uvIndexTime")
    private Long uvIndexTime;
    @JsonProperty("visibility")
    private Long visibility;
    @JsonProperty("ozone")
    private Double ozone;
    @JsonProperty("temperatureMin")
    private Double temperatureMin;
    @JsonProperty("temperatureMinTime")
    private Long temperatureMinTime;
    @JsonProperty("temperatureMax")
    private Double temperatureMax;
    @JsonProperty("temperatureMaxTime")
    private Long temperatureMaxTime;
    @JsonProperty("apparentTemperatureMin")
    private Double apparentTemperatureMin;
    @JsonProperty("apparentTemperatureMinTime")
    private Long apparentTemperatureMinTime;
    @JsonProperty("apparentTemperatureMax")
    private Double apparentTemperatureMax;
    @JsonProperty("apparentTemperatureMaxTime")
    private Long apparentTemperatureMaxTime;

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

    @JsonProperty("sunriseTime")
    public Long getSunriseTime() {
        return sunriseTime;
    }

    @JsonProperty("sunriseTime")
    public void setSunriseTime(Long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    @JsonProperty("sunsetTime")
    public Long getSunsetTime() {
        return sunsetTime;
    }

    @JsonProperty("sunsetTime")
    public void setSunsetTime(Long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    @JsonProperty("moonPhase")
    public Double getMoonPhase() {
        return moonPhase;
    }

    @JsonProperty("moonPhase")
    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    @JsonProperty("precipIntensity")
    public Long getPrecipIntensity() {
        return precipIntensity;
    }

    @JsonProperty("precipIntensity")
    public void setPrecipIntensity(Long precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    @JsonProperty("precipIntensityMax")
    public Long getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    @JsonProperty("precipIntensityMax")
    public void setPrecipIntensityMax(Long precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    @JsonProperty("precipIntensityMaxTime")
    public Long getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    @JsonProperty("precipIntensityMaxTime")
    public void setPrecipIntensityMaxTime(Long precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    @JsonProperty("precipProbability")
    public Long getPrecipProbability() {
        return precipProbability;
    }

    @JsonProperty("precipProbability")
    public void setPrecipProbability(Long precipProbability) {
        this.precipProbability = precipProbability;
    }

    @JsonProperty("precipType")
    public String getPrecipType() {
        return precipType;
    }

    @JsonProperty("precipType")
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    @JsonProperty("temperatureHigh")
    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    @JsonProperty("temperatureHigh")
    public void setTemperatureHigh(Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    @JsonProperty("temperatureHighTime")
    public Long getTemperatureHighTime() {
        return temperatureHighTime;
    }

    @JsonProperty("temperatureHighTime")
    public void setTemperatureHighTime(Long temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    @JsonProperty("temperatureLow")
    public Double getTemperatureLow() {
        return temperatureLow;
    }

    @JsonProperty("temperatureLow")
    public void setTemperatureLow(Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    @JsonProperty("temperatureLowTime")
    public Long getTemperatureLowTime() {
        return temperatureLowTime;
    }

    @JsonProperty("temperatureLowTime")
    public void setTemperatureLowTime(Long temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
    }

    @JsonProperty("apparentTemperatureHigh")
    public Double getApparentTemperatureHigh() {
        return apparentTemperatureHigh;
    }

    @JsonProperty("apparentTemperatureHigh")
    public void setApparentTemperatureHigh(Double apparentTemperatureHigh) {
        this.apparentTemperatureHigh = apparentTemperatureHigh;
    }

    @JsonProperty("apparentTemperatureHighTime")
    public Long getApparentTemperatureHighTime() {
        return apparentTemperatureHighTime;
    }

    @JsonProperty("apparentTemperatureHighTime")
    public void setApparentTemperatureHighTime(Long apparentTemperatureHighTime) {
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
    }

    @JsonProperty("apparentTemperatureLow")
    public Double getApparentTemperatureLow() {
        return apparentTemperatureLow;
    }

    @JsonProperty("apparentTemperatureLow")
    public void setApparentTemperatureLow(Double apparentTemperatureLow) {
        this.apparentTemperatureLow = apparentTemperatureLow;
    }

    @JsonProperty("apparentTemperatureLowTime")
    public Long getApparentTemperatureLowTime() {
        return apparentTemperatureLowTime;
    }

    @JsonProperty("apparentTemperatureLowTime")
    public void setApparentTemperatureLowTime(Long apparentTemperatureLowTime) {
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
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

    @JsonProperty("windGustTime")
    public Long getWindGustTime() {
        return windGustTime;
    }

    @JsonProperty("windGustTime")
    public void setWindGustTime(Long windGustTime) {
        this.windGustTime = windGustTime;
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

    @JsonProperty("uvIndexTime")
    public Long getUvIndexTime() {
        return uvIndexTime;
    }

    @JsonProperty("uvIndexTime")
    public void setUvIndexTime(Long uvIndexTime) {
        this.uvIndexTime = uvIndexTime;
    }

    @JsonProperty("visibility")
    public Long getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Long visibility) {
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

    @JsonProperty("temperatureMin")
    public Double getTemperatureMin() {
        return temperatureMin;
    }

    @JsonProperty("temperatureMin")
    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    @JsonProperty("temperatureMinTime")
    public Long getTemperatureMinTime() {
        return temperatureMinTime;
    }

    @JsonProperty("temperatureMinTime")
    public void setTemperatureMinTime(Long temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    @JsonProperty("temperatureMax")
    public Double getTemperatureMax() {
        return temperatureMax;
    }

    @JsonProperty("temperatureMax")
    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    @JsonProperty("temperatureMaxTime")
    public Long getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    @JsonProperty("temperatureMaxTime")
    public void setTemperatureMaxTime(Long temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    @JsonProperty("apparentTemperatureMin")
    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    @JsonProperty("apparentTemperatureMin")
    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    @JsonProperty("apparentTemperatureMinTime")
    public Long getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    @JsonProperty("apparentTemperatureMinTime")
    public void setApparentTemperatureMinTime(Long apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    @JsonProperty("apparentTemperatureMax")
    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    @JsonProperty("apparentTemperatureMax")
    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    @JsonProperty("apparentTemperatureMaxTime")
    public Long getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    @JsonProperty("apparentTemperatureMaxTime")
    public void setApparentTemperatureMaxTime(Long apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }
}
