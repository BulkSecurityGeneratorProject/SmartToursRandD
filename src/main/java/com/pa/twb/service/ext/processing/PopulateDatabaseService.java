package com.pa.twb.service.ext.processing;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.ext.ExtAttractionRepository;
import com.pa.twb.service.ext.processing.dto.weather.DailyData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopulateDatabaseService {

    private final ExtAttractionRepository extAttractionRepository;

    private final DarkSkyWeatherService darkSkyWeatherService;

    public PopulateDatabaseService(ExtAttractionRepository extAttractionRepository,
                                   DarkSkyWeatherService darkSkyWeatherService) {
        this.extAttractionRepository = extAttractionRepository;
        this.darkSkyWeatherService = darkSkyWeatherService;
    }

    //    @Scheduled(initialDelay = 5000L, fixedDelay = 1000000L)
    public void populate() {
        List<Attraction> attractions = extAttractionRepository.findAll();

        for (Attraction attraction : attractions) {

            DailyData dailyData = darkSkyWeatherService.getWeatherForecastTomorrow(attraction.getLatitude(), attraction.getLongitude());
            attraction.setDsSummary(dailyData.getSummary());
            attraction.setDsIcon(dailyData.getIcon());
            attraction.setDsApparentTemperatureHigh(dailyData.getApparentTemperatureHigh());
            attraction.setDsApparentTemperatureLow(dailyData.getApparentTemperatureLow());
            attraction.setDsDewPoint(dailyData.getDewPoint());
            attraction.setDsHumidity(dailyData.getHumidity());
            attraction.setDsPressure(dailyData.getPressure());
            attraction.setDsWindSpeed(dailyData.getWindSpeed());
            attraction.setDsWindGust(dailyData.getWindGust());
            attraction.setDsCloudCover(dailyData.getCloudCover());
            attraction.setDsVisibility(dailyData.getVisibility());

            extAttractionRepository.save(attraction);
        }
    }
}
