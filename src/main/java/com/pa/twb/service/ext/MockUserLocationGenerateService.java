package com.pa.twb.service.ext;

import com.pa.twb.service.ext.dto.location.LocationDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MockUserLocationGenerateService {
    private static final int USER_COUNT = 30;

    private static final double SOUTH_WEST_LAT_COORD = 54.084530;
    private static final double SOUTH_WEST_LONG_COORD = -8.012903;

    private static final double NORTH_EAST_LAT_COORD = 55.244539;
    private static final double NORTH_EAST_LONG_COORD = -5.551857;

    public List<LocationDTO> generate() {
        List<LocationDTO> locations = new ArrayList<>();
        for (int index = 0; index < USER_COUNT; index++) {
            double randomLat = generateRandom(SOUTH_WEST_LAT_COORD, NORTH_EAST_LAT_COORD);
            double randomLong = generateRandom(NORTH_EAST_LONG_COORD, SOUTH_WEST_LONG_COORD);

            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLatitude(randomLat);
            locationDTO.setLongitude(randomLong);

            locations.add(locationDTO);
        }
        return locations;
    }

    private double generateRandom(double min, double max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }
}
