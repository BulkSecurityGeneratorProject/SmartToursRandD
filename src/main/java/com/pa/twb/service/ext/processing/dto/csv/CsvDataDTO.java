package com.pa.twb.service.ext.processing.dto.csv;

public class CsvDataDTO {

    private long id;
    private double distance;
    private double avgTemp;
    private double cloudCover;
    private double windSpeed;
    private double weight;
    private boolean takenAction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public boolean isTakenAction() {
        return takenAction;
    }

    public void setTakenAction(boolean takenAction) {
        this.takenAction = takenAction;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
