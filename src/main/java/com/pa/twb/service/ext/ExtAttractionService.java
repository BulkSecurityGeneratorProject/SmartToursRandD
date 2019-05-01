package com.pa.twb.service.ext;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.AttractionRepository;
import com.pa.twb.repository.ext.ExtAttractionRepository;
import com.pa.twb.service.AttractionService;
import com.pa.twb.service.ext.dto.attraction.CreateAttractionDTO;
import com.pa.twb.service.ext.dto.attraction.GetAttractionDTO;
import com.pa.twb.service.ext.dto.attraction.GetAttractionWithDistanceDTO;
import com.pa.twb.service.ext.dto.attraction.UpdateAttractionDTO;
import com.pa.twb.service.ext.processing.dto.location.GetEntityWithLocationDTO;
import com.pa.twb.service.mapper.ext.ExtAttractionMapper;
import com.pa.twb.web.rest.errors.ext.AttractionNotFoundException;
import com.pa.twb.web.rest.errors.ext.NoLocationProvidedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExtAttractionService extends AttractionService {
    private final ExtAttractionRepository extAttractionRepository;

    private final ExtAttractionMapper extAttractionMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ExtAttractionService(AttractionRepository attractionRepository,
                                ExtAttractionRepository extAttractionRepository,
                                ExtAttractionMapper extAttractionMapper) {
        super(attractionRepository);
        this.extAttractionRepository = extAttractionRepository;
        this.extAttractionMapper = extAttractionMapper;
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

    @Transactional(readOnly = true)
    public Page<GetAttractionWithDistanceDTO> getAllByLocation(Pageable pageable, Double latitude, Double longitude, Double radius) {
        if (latitude == null || longitude == null) {
            throw new NoLocationProvidedException();
        }
        if (radius == null) {
            radius = 100d;
        }
        Page<GetEntityWithLocationDTO> page = extAttractionRepository.findByDistance(latitude, longitude, pageable);
        List<GetAttractionWithDistanceDTO> listPage = page.getContent().stream().
            map(getEntityWithLocationDto -> {
                Long attractionId = getEntityWithLocationDto.getId();
                Optional<Attraction> attractionOpt = extAttractionRepository.findById(attractionId);
                if (attractionOpt.isPresent()) {
                    GetAttractionWithDistanceDTO getSiteWithDistanceDTO =
                        extAttractionMapper.entityToGetWithDistanceDto(attractionOpt.get());
                    getSiteWithDistanceDTO.setDistance(getEntityWithLocationDto.getDistance());
                    return getSiteWithDistanceDTO;
                }
                return null;
            }).collect(Collectors.toList());
        return new PageImpl<>(listPage, pageable, listPage.size());
    }
}
