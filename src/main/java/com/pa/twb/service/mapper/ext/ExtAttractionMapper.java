package com.pa.twb.service.mapper.ext;

import com.pa.twb.domain.Attraction;
import com.pa.twb.service.ext.dto.attraction.CreateAttractionDTO;
import com.pa.twb.service.ext.dto.attraction.GetAttractionDTO;
import com.pa.twb.service.ext.dto.attraction.GetAttractionWithDistanceDTO;
import com.pa.twb.service.ext.dto.attraction.UpdateAttractionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = ALWAYS,
    uses = {,}
)
@SuppressWarnings("unused")
public interface ExtAttractionMapper {
    Attraction createDtoToEntity(CreateAttractionDTO createAttractionDto);

    GetAttractionDTO entityToGetDto(Attraction attraction);

    Attraction updateEntity(UpdateAttractionDTO updateAttractionDto,
                            @MappingTarget Attraction attraction);

    GetAttractionWithDistanceDTO entityToGetWithDistanceDto(Attraction attraction);
}
