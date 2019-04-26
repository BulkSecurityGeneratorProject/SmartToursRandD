
package com.pa.twb.service.ext.processing.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Minutely {

    @JsonProperty("summary")
    private String summary;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("data")
    private List<MinutelyData> data = new ArrayList<MinutelyData>();

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

    @JsonProperty("data")
    public List<MinutelyData> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<MinutelyData> data) {
        this.data = data;
    }

}
