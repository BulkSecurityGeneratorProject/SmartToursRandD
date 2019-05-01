package com.pa.twb.service.ext.dto.attraction;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class GetAttractionWithDistanceDTO {
    @Min(1L)
    @NotNull
    private Long id;

    private String sygicTravelId;

    private Double rating;

    private Double latitude;

    private Double longitude;

    private String name;

    private String marker;

    @Lob
    private String perex;

    private String thumbnailUrl;

    private String categories;

    @Lob
    private String dsSummary;

    private String dsIcon;

    private Double dsApparentTemperatureHigh;

    private Double dsApparentTemperatureLow;

    private Double dsDewPoint;

    private Double dsHumidity;

    private Double dsPressure;

    private Double dsWindSpeed;

    private Double dsWindGust;

    private Double dsCloudCover;

    private Long dsVisibility;

    private Double distance;

    public GetAttractionWithDistanceDTO() {
    }

    public GetAttractionWithDistanceDTO(@Min(1L) @NotNull Long id, String sygicTravelId, Double rating, Double latitude, Double longitude, String name, String marker, String perex, String thumbnailUrl, String categories, String dsSummary, String dsIcon, Double dsApparentTemperatureHigh, Double dsApparentTemperatureLow, Double dsDewPoint, Double dsHumidity, Double dsPressure, Double dsWindSpeed, Double dsWindGust, Double dsCloudCover, Long dsVisibility, Double distance) {
        this.id = id;
        this.sygicTravelId = sygicTravelId;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.marker = marker;
        this.perex = perex;
        this.thumbnailUrl = thumbnailUrl;
        this.categories = categories;
        this.dsSummary = dsSummary;
        this.dsIcon = dsIcon;
        this.dsApparentTemperatureHigh = dsApparentTemperatureHigh;
        this.dsApparentTemperatureLow = dsApparentTemperatureLow;
        this.dsDewPoint = dsDewPoint;
        this.dsHumidity = dsHumidity;
        this.dsPressure = dsPressure;
        this.dsWindSpeed = dsWindSpeed;
        this.dsWindGust = dsWindGust;
        this.dsCloudCover = dsCloudCover;
        this.dsVisibility = dsVisibility;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSygicTravelId() {
        return sygicTravelId;
    }

    public void setSygicTravelId(String sygicTravelId) {
        this.sygicTravelId = sygicTravelId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getPerex() {
        return perex;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDsSummary() {
        return dsSummary;
    }

    public void setDsSummary(String dsSummary) {
        this.dsSummary = dsSummary;
    }

    public String getDsIcon() {
        return dsIcon;
    }

    public void setDsIcon(String dsIcon) {
        this.dsIcon = dsIcon;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDsApparentTemperatureHigh() {
        return dsApparentTemperatureHigh;
    }

    public void setDsApparentTemperatureHigh(Double dsApparentTemperatureHigh) {
        this.dsApparentTemperatureHigh = dsApparentTemperatureHigh;
    }

    public Double getDsApparentTemperatureLow() {
        return dsApparentTemperatureLow;
    }

    public void setDsApparentTemperatureLow(Double dsApparentTemperatureLow) {
        this.dsApparentTemperatureLow = dsApparentTemperatureLow;
    }

    public Double getDsDewPoint() {
        return dsDewPoint;
    }

    public void setDsDewPoint(Double dsDewPoint) {
        this.dsDewPoint = dsDewPoint;
    }

    public Double getDsHumidity() {
        return dsHumidity;
    }

    public void setDsHumidity(Double dsHumidity) {
        this.dsHumidity = dsHumidity;
    }

    public Double getDsPressure() {
        return dsPressure;
    }

    public void setDsPressure(Double dsPressure) {
        this.dsPressure = dsPressure;
    }

    public Double getDsWindSpeed() {
        return dsWindSpeed;
    }

    public void setDsWindSpeed(Double dsWindSpeed) {
        this.dsWindSpeed = dsWindSpeed;
    }

    public Double getDsWindGust() {
        return dsWindGust;
    }

    public void setDsWindGust(Double dsWindGust) {
        this.dsWindGust = dsWindGust;
    }

    public Double getDsCloudCover() {
        return dsCloudCover;
    }

    public void setDsCloudCover(Double dsCloudCover) {
        this.dsCloudCover = dsCloudCover;
    }

    public Long getDsVisibility() {
        return dsVisibility;
    }

    public void setDsVisibility(Long dsVisibility) {
        this.dsVisibility = dsVisibility;
    }
}
