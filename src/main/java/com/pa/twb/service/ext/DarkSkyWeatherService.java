package com.pa.twb.service.ext;

import com.pa.twb.service.ext.dto.weather.Daily;
import com.pa.twb.service.ext.dto.weather.DailyData;
import com.pa.twb.service.ext.dto.weather.DarkSkyWeatherDTO;
import com.pa.twb.web.rest.errors.ext.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class DarkSkyWeatherService {
    private final Logger log = LoggerFactory.getLogger(ScheduledTrainerService.class);

    private static final String DARKSKY_API_KEY = "49f20ae065b96f812b1bd0578e7ce407";
    private static final String DARKSKY_URL = "https://api.darksky.net/forecast/" + DARKSKY_API_KEY + "/%f,%f,%d";

    public DailyData getWeatherForecastTomorrow(double latitude, double longitude) {
        Instant nowInstant = Instant.now();
        Instant timeTomorrow = nowInstant.plus(1L, ChronoUnit.DAYS);
        long tomorrowEpochSeconds = timeTomorrow.toEpochMilli() / 1000;

        String urlString = String.format(DARKSKY_URL, latitude, longitude, tomorrowEpochSeconds);
        URI uri = URI.create(urlString);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DarkSkyWeatherDTO> response = restTemplate.getForEntity(uri, DarkSkyWeatherDTO.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            DarkSkyWeatherDTO darkSkyWeatherDTO = response.getBody();
            if (darkSkyWeatherDTO != null) {
                Daily daily = darkSkyWeatherDTO.getDaily();
                if (daily != null) {
                    if (!daily.getData().isEmpty()) {
                        return daily.getData().get(0);
                    }
                }
            }
        }
        throw new ApiException();
    }
}
