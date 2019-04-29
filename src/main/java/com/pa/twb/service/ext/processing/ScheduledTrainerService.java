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
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTrainerService {

    private static final int RANDOM_ATTR_COUNT = 5;

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

    //    @Scheduled(initialDelay = 5000L, fixedDelay = 10000L)
    public void train() {
        List<CsvDataDTO> dataset = new ArrayList<>();

        List<Attraction> attractions = extAttractionRepository.findAllRandomOrder();

        int attractionCount = attractions.size();
//        int attractionCount = RANDOM_ATTR_COUNT;

        for (int index = 0; index < attractionCount; index++) {

            Attraction attraction = attractions.get(index);

            double attrLatitude = attraction.getLatitude();
            double attrLongitude = attraction.getLongitude();

            DailyData tomorrowsWeather = darkSkyWeatherService.
                getWeatherForecastTomorrow(attrLatitude, attrLongitude);

            double apparentTempHigh = tomorrowsWeather.getApparentTemperatureHigh();
            double apparentTempLow = tomorrowsWeather.getApparentTemperatureLow();
            double avgApparentTemperatureFarenheit = (apparentTempHigh + apparentTempLow) / 2;

            double avgApparentTemperatureCelcius = ((5 * (avgApparentTemperatureFarenheit - 32.0)) / 9.0);

            BigDecimal avgApparentTempBigDecimal = new BigDecimal(avgApparentTemperatureCelcius).
                setScale(2, RoundingMode.HALF_UP);

            List<LocationDTO> randomUserLocList = locationGenerateService.generate();

            for (LocationDTO userLocation : randomUserLocList) {
                double userLatitude = userLocation.getLatitude();
                double userLongitude = userLocation.getLongitude();

                double userDistanceInMiles = DistanceUtil.
                    distance(userLatitude, userLongitude, attrLatitude, attrLongitude, 'M');

                CsvDataDTO csvDataDTO = new CsvDataDTO();
                csvDataDTO.setId(attraction.getId());
                csvDataDTO.setDistance(userDistanceInMiles);
                csvDataDTO.setAvgTemp(avgApparentTempBigDecimal.doubleValue());
                csvDataDTO.setCloudCover(tomorrowsWeather.getCloudCover());
                csvDataDTO.setWindSpeed(tomorrowsWeather.getWindSpeed());

                dataset.add(csvDataDTO);
            }
        }

        // get min and maxes used to base bias
        double minDistance = Double.MAX_VALUE;
        double maxDistance = 0d;
        double minAvgTemp = Double.MAX_VALUE;
        double maxAvgTemp = 0d;

        double maxWindSpeed = 0d;
        for (CsvDataDTO csvDataDTO : dataset) {
            if (csvDataDTO.getDistance() < minDistance) {
                minDistance = csvDataDTO.getDistance();
            }
            if (csvDataDTO.getDistance() > maxDistance) {
                maxDistance = csvDataDTO.getDistance();
            }
            if (csvDataDTO.getAvgTemp() < minAvgTemp) {
                minAvgTemp = csvDataDTO.getAvgTemp();
            }
            if (csvDataDTO.getAvgTemp() > maxAvgTemp) {
                maxAvgTemp = csvDataDTO.getAvgTemp();
            }
            if (csvDataDTO.getWindSpeed() > maxWindSpeed) {
                maxWindSpeed = csvDataDTO.getWindSpeed();
            }
        }

        // all weightings are guesswork here for bias and estimation to get action taken which looks about right for distance and weather.

        double maxMinDistnaceDiff = maxDistance - minDistance;
        double maxMinAvgTempDiff = maxAvgTemp - minAvgTemp;
        for (CsvDataDTO csvDataDTO : dataset) {
            double thisDistance = csvDataDTO.getDistance();
            double thisDistNorm = thisDistance - minDistance;
//            subtracting from 1 for the reverse
            double distWeighting = 1 - (thisDistNorm / maxMinDistnaceDiff);

            double thisAvgTemp = csvDataDTO.getAvgTemp();
            double thisAvgTempNorm = thisAvgTemp - minAvgTemp;
            double avgTempWeighting = thisAvgTempNorm / maxMinAvgTempDiff;
//
//            double cloudCoverWeighting = 1 - csvDataDTO.getCloudCover();
//
//            double thisWindSpeed = csvDataDTO.getAvgTemp();
//            double windSpeedWeighting = 1 - (thisWindSpeed / maxWindSpeed);

            double weighting = (distWeighting + avgTempWeighting) / 2;

//            reduce weighting by 5 percent as people may not even bother booking. will get more falses
            weighting = weighting - (weighting / 20);
            csvDataDTO.setWeight(weighting);

            csvDataDTO.setTakenAction(Math.random() <= weighting);
        }

        try {
            csvService.write(dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
