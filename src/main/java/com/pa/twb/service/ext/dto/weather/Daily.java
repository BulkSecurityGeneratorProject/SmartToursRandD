
package com.pa.twb.service.ext.dto.weather;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Daily {

    @JsonProperty("summary")
    private String summary;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("data")
    private List<DailyData> data = new ArrayList<DailyData>();

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
    public List<DailyData> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<DailyData> data) {
        this.data = data;
    }
}
