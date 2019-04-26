package com.pa.twb.service.ext.processing;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.ext.ExtAttractionPurchaseRepository;
import com.pa.twb.repository.ext.ExtAttractionRepository;
import com.pa.twb.service.ext.processing.dto.csv.CsvDataDTO;
import com.pa.twb.service.ext.processing.dto.location.LocationDTO;
import com.pa.twb.service.ext.processing.dto.weather.DailyData;
import com.pa.twb.service.ext.util.DistanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ScheduledTrainerService {

    private static final int RANDOM_ATTR_COUNT = 15;


    private final Logger log = LoggerFactory.getLogger(ScheduledTrainerService.class);
    private final ExtAttractionRepository extAttractionRepository;
    private final ExtAttractionPurchaseRepository extAttractionPurchaseRepository;
    private final MockUserLocationGenerateService locationGenerateService;
    private final DarkSkyWeatherService darkSkyWeatherService;
    private final CsvService csvService;

    public ScheduledTrainerService(ExtAttractionRepository extAttractionRepository,
                                   ExtAttractionPurchaseRepository extAttractionPurchaseRepository,
                                   MockUserLocationGenerateService locationGenerateService,
                                   DarkSkyWeatherService darkSkyWeatherService,
                                   CsvService csvService) {
        this.extAttractionRepository = extAttractionRepository;
        this.extAttractionPurchaseRepository = extAttractionPurchaseRepository;
        this.locationGenerateService = locationGenerateService;
        this.darkSkyWeatherService = darkSkyWeatherService;
        this.csvService = csvService;
    }

    @Scheduled(initialDelay = 5000L, fixedDelay = 10000L)
    public void train() {
        List<LocationDTO> randomUserLocList = locationGenerateService.generate();
        List<CsvDataDTO> dataset = new ArrayList<>();

        List<Attraction> attractions = extAttractionRepository.findAllRandomOrder();

        for (int index = 0; index < RANDOM_ATTR_COUNT; index++) {

            Attraction attraction = attractions.get(index);

            double attrLatitude = attraction.getLat();
            double attrLongitude = attraction.getLng();

            DailyData tomorrowsCurrentWeather = darkSkyWeatherService.
                getWeatherForecastTomorrow(attrLatitude, attrLongitude);

            Long dailyTimeLong = tomorrowsCurrentWeather.getTime();
            Instant dailyInstant = Instant.ofEpochSecond(dailyTimeLong);
            String dailyIcon = tomorrowsCurrentWeather.getIcon();

            double apparentTempHigh = tomorrowsCurrentWeather.getApparentTemperatureHigh();
            double apparentTempLow = tomorrowsCurrentWeather.getApparentTemperatureLow();
            double avgApparentTemperatureFarenheit = (apparentTempHigh + apparentTempLow) / 2;

            double avgApparentTemperatureCelcius = ((5 * (avgApparentTemperatureFarenheit - 32.0)) / 9.0);

            BigDecimal avgApparentTempBigDecimal = new BigDecimal(avgApparentTemperatureCelcius).
                setScale(2, RoundingMode.HALF_UP);

            for (LocationDTO userLocation : randomUserLocList) {
                double userLatitude = userLocation.getLatitude();
                double userLongitude = userLocation.getLongitude();

                double userDistanceInMiles = DistanceUtil.
                    distance(userLatitude, userLongitude, attrLatitude, attrLongitude, 'M');

                Random random = new Random();
                int randomNumber = random.nextInt(10);
                boolean actionTaken = (randomNumber > 5);
                CsvDataDTO csvDataDTO = new CsvDataDTO();
                csvDataDTO.setId(attraction.getId());
                csvDataDTO.setDistance(userDistanceInMiles);
                csvDataDTO.setWeatherStatus(dailyIcon);
                csvDataDTO.setAvgTemp(avgApparentTempBigDecimal.doubleValue());
                csvDataDTO.setTakenAction(actionTaken);

                dataset.add(csvDataDTO);
            }
        }
        try {
            csvService.write(dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
