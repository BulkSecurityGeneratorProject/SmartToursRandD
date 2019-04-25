package com.pa.twb.service.ext;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.ext.ExtAttractionPurchaseRepository;
import com.pa.twb.service.ext.dto.weather.DailyData;
import com.pa.twb.service.ext.util.DistanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Component
@Transactional(readOnly = true)
public class ScheduledTrainerService {
    private final Logger log = LoggerFactory.getLogger(ScheduledTrainerService.class);

    private final ExtAttractionService extAttractionService;

    private final ExtAttractionPurchaseRepository extAttractionPurchaseRepository;

    private final DarkSkyWeatherService darkSkyWeatherService;

    public ScheduledTrainerService(ExtAttractionService extAttractionService,
                                   ExtAttractionPurchaseRepository extAttractionPurchaseRepository,
                                   DarkSkyWeatherService darkSkyWeatherService) {
        this.extAttractionService = extAttractionService;
        this.extAttractionPurchaseRepository = extAttractionPurchaseRepository;
        this.darkSkyWeatherService = darkSkyWeatherService;
    }

    @Scheduled(initialDelay = 5000L, fixedDelay = 10000L)
    public void train() {
        Long attractionId = 1L;

        double userLatitude = 54.599869d;
        double userLongitude = -5.9276189d;

        Attraction attraction = extAttractionService.findByIdThrowException(attractionId);

        double attrLatitude = attraction.getLatitude();
        double attrLongitude = attraction.getLongitude();

        double userDistanceInMiles = DistanceUtil.distance(userLatitude, userLongitude, attrLatitude, attrLongitude, 'M');

        DailyData tomorrowsCurrentWeather = darkSkyWeatherService.getWeatherForecastTomorrow(attrLatitude, attrLongitude);

        Long dailyTimeLong = tomorrowsCurrentWeather.getTime();
        Instant dailyInstant = Instant.ofEpochSecond(dailyTimeLong);
        String dailyIcon = tomorrowsCurrentWeather.getIcon();

        double apparentTempHigh = tomorrowsCurrentWeather.getApparentTemperatureHigh();
        double apparentTempLow = tomorrowsCurrentWeather.getApparentTemperatureLow();
        double avgApparentTemperatureFarenheit = (apparentTempHigh + apparentTempLow) / 2;

        double avgApparentTemperatureCelcius = ((5 * (avgApparentTemperatureFarenheit - 32.0)) / 9.0);

        BigDecimal avgApparentTempBigDecimal = new BigDecimal(avgApparentTemperatureCelcius).setScale(2, RoundingMode.HALF_UP);




    }

//    split the distance into a category, used to determine how far away
    private String getDistanceCategoryForDistance(double distance, double largestLandBound) {
        final int categoryRange = 32;
        double landSegmentSize = largestLandBound / categoryRange;
        int counter = categoryRange;
        double thisSegmentSize = largestLandBound;
        while (thisSegmentSize > 0) {
            if (distance > thisSegmentSize) {
                return "distance-seg-" + counter;
            }
            counter--;
            thisSegmentSize -= landSegmentSize;
        }
        return "unknown-distance";
    }
}
