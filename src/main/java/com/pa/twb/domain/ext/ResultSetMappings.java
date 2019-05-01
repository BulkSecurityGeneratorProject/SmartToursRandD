package com.pa.twb.domain.ext;


import com.pa.twb.service.ext.dto.attraction.GetAttractionWithDistanceDTO;

import javax.persistence.*;

@Entity
@SqlResultSetMapping(
    name = "GetAttractionWithDistanceDTO",
    classes = @ConstructorResult(
        targetClass = GetAttractionWithDistanceDTO.class,
        columns = {
            @ColumnResult(name = "id", type = Long.class),
            @ColumnResult(name = "sygicTravelId", type = String.class),
            @ColumnResult(name = "rating", type = Double.class),
            @ColumnResult(name = "latitude", type = Double.class),
            @ColumnResult(name = "longitude", type = Double.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "marker", type = String.class),
            @ColumnResult(name = "perex", type = String.class),
            @ColumnResult(name = "thumbnailUrl", type = String.class),
            @ColumnResult(name = "categories", type = String.class),
            @ColumnResult(name = "dsSummary", type = String.class),
            @ColumnResult(name = "dsIcon", type = String.class),
            @ColumnResult(name = "dsApparentTemperatureHigh", type = Double.class),
            @ColumnResult(name = "dsApparentTemperatureLow", type = Double.class),
            @ColumnResult(name = "dsDewPoint", type = Double.class),
            @ColumnResult(name = "dsHumidity", type = Double.class),
            @ColumnResult(name = "dsPressure", type = Double.class),
            @ColumnResult(name = "dsWindSpeed", type = Double.class),
            @ColumnResult(name = "dsWindGust", type = Double.class),
            @ColumnResult(name = "dsCloudCover", type = Double.class),
            @ColumnResult(name = "dsVisibility", type = Long.class),
            @ColumnResult(name = "distance", type = Double.class)
        }))
public class ResultSetMappings {
    @Id
    public Long id;
}
