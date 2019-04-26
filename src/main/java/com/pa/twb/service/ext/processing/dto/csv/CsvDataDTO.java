package com.pa.twb.service.ext.processing.dto.csv;

public class CsvDataDTO {

    private double distance;
    private String weatherStatus;
    private boolean takenAction;

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

    public boolean isTakenAction() {
        return takenAction;
    }

    public void setTakenAction(boolean takenAction) {
        this.takenAction = takenAction;
    }
}
