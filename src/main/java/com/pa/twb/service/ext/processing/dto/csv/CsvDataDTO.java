package com.pa.twb.service.ext.processing.dto.csv;

public class CsvDataDTO {

    private long id;
    private double distance;
    private String weatherStatus;
    private double avgTemp;
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

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus;
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
}
