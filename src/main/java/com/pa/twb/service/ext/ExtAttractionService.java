package com.pa.twb.service.ext;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.AttractionRepository;
import com.pa.twb.repository.ext.ExtAttractionRepository;
import com.pa.twb.service.AttractionService;
import com.pa.twb.service.ext.dto.attraction.*;
import com.pa.twb.service.mapper.ext.ExtAttractionMapper;
import com.pa.twb.web.rest.errors.ext.AttractionNotFoundException;
import com.pa.twb.web.rest.errors.ext.NoLocationProvidedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URI;
import java.util.*;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExtAttractionService extends AttractionService {
    private final Logger log = LoggerFactory.getLogger(ExtAttractionService.class);

    private final ExtAttractionRepository extAttractionRepository;

    private final ExtAttractionMapper extAttractionMapper;

    private final EntityManager em;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ExtAttractionService(AttractionRepository attractionRepository,
                                ExtAttractionRepository extAttractionRepository,
                                ExtAttractionMapper extAttractionMapper,
                                EntityManager em) {
        super(attractionRepository);
        this.extAttractionRepository = extAttractionRepository;
        this.extAttractionMapper = extAttractionMapper;
        this.em = em;
    }

    public GetAttractionDTO create(CreateAttractionDTO createAttractionDto) {
        Attraction attraction = extAttractionMapper.createDtoToEntity(createAttractionDto);
        attraction = save(attraction);
        return extAttractionMapper.entityToGetDto(attraction);
    }

    public GetAttractionDTO update(UpdateAttractionDTO updateAttractionDto) {
        Attraction result = findByIdThrowException(updateAttractionDto.getId());
        result = extAttractionMapper.updateEntity(updateAttractionDto, result);
        return extAttractionMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public Attraction findByIdThrowException(Long id) {
        return extAttractionRepository.findById(id).orElseGet(() -> {
            throw new AttractionNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public GetAttractionDTO getById(Long id) {
        Attraction result = findByIdThrowException(id);
        return extAttractionMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public Page<GetAttractionDTO> getAll(Pageable pageable) {
        Page<Attraction> page = extAttractionRepository.findAll(pageable);
        return page.map(extAttractionMapper::entityToGetDto);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<GetAttractionWithDistanceDTO> getAllByLocation(Pageable pageable, Double latitude, Double longitude) {
        Query query = getAttractionsByDistnaceQuery(latitude, longitude);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult((int) pageable.getOffset());

        List<GetAttractionWithDistanceDTO> locations = (List<GetAttractionWithDistanceDTO>) query.getResultList();
        return new PageImpl<>(locations, pageable, locations.size());
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Optional<List<GetRecommendationDTO>> getAllByRecommendation(Double latitude, Double longitude) {
        Query query = getAttractionsByDistnaceQuery(latitude, longitude);
        List<GetAttractionWithDistanceDTO> locations = (List<GetAttractionWithDistanceDTO>) query.getResultList();

        List<RequestRecommendationDTO> requestRecommendList = new ArrayList<>();
        for (GetAttractionWithDistanceDTO attractionWithDistance : locations) {
            RequestRecommendationDTO requestRecommendationDTO = new RequestRecommendationDTO();
            requestRecommendationDTO.setItemId(attractionWithDistance.getId());
            requestRecommendationDTO.setDistance(attractionWithDistance.getDistance());
            requestRecommendList.add(requestRecommendationDTO);
        }
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://127.0.0.1:8000/logistic-regression/");

        GetRecommendationDTO[] recommendations = restTemplate.
            postForObject(uri, requestRecommendList, GetRecommendationDTO[].class);
        if (recommendations == null) {
//            might want to return something instead of nothing
            return Optional.empty();
        }
//        set attraction to recommendation
        for (GetRecommendationDTO recommendation : recommendations) {
            GetAttractionWithDistanceDTO attractionWithDistance =
                getAttractionWithDistanceForId(locations, recommendation.getItemId());
            recommendation.setAttraction(attractionWithDistance);
        }

//        sort based on probability
        ArrayList<GetRecommendationDTO> recommendationList = new ArrayList<>(Arrays.asList(recommendations));
        recommendationList.sort(Comparator.comparing(GetRecommendationDTO::getProbability).reversed());
        return Optional.of(recommendationList);
    }

    private Query getAttractionsByDistnaceQuery(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new NoLocationProvidedException();
        }
        Query query = em.createNativeQuery("SELECT " +
            "    attraction.id AS id, " +
            "    attraction.sygic_travel_id AS sygicTravelId, " +
            "    attraction.rating AS rating, " +
            "    attraction.latitude AS latitude, " +
            "    attraction.longitude AS longitude, " +
            "    attraction.name AS name, " +
            "    attraction.marker AS marker, " +
            "    attraction.perex AS perex, " +
            "    attraction.thumbnail_url AS thumbnailUrl, " +
            "    attraction.categories AS categories, " +
            "    attraction.ds_summary AS dsSummary, " +
            "    attraction.ds_icon AS dsIcon, " +
            "    attraction.ds_apparent_temperature_high AS dsApparentTemperatureHigh, " +
            "    attraction.ds_apparent_temperature_low AS dsApparentTemperatureLow, " +
            "    attraction.ds_dew_point AS dsDewPoint, " +
            "    attraction.ds_humidity AS dsHumidity, " +
            "    attraction.ds_pressure AS dsPressure, " +
            "    attraction.ds_wind_speed AS dsWindSpeed, " +
            "    attraction.ds_wind_gust AS dsWindGust, " +
            "    attraction.ds_cloud_cover AS dsCloudCover, " +
            "    attraction.ds_visibility AS dsVisibility, " +
            "    (6371 * acos(cos(radians(:longitude)) " +
            "                 * cos(radians(attraction.longitude)) " +
            "                 * cos(radians(attraction.latitude) - radians(:latitude)) " +
            "                 + sin(radians(:longitude)) " +
            "                   * sin(radians(attraction.longitude)))) AS distance " +
            "FROM attraction attraction " +
            "ORDER BY distance", "GetAttractionWithDistanceDTO");
        query.setParameter("latitude", latitude);
        query.setParameter("longitude", longitude);
        return query;
    }

    private GetAttractionWithDistanceDTO getAttractionWithDistanceForId(List<GetAttractionWithDistanceDTO> list, Long id) {
        for (GetAttractionWithDistanceDTO attractionWithDistance : list) {
            if (Objects.equals(attractionWithDistance.getId(), id)) {
                return attractionWithDistance;
            }
        }
        return null;
    }
}
