package com.pa.twb.service.mapper.ext;

import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.service.ext.dto.attractioneventtype.CreateAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.GetAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.UpdateAttractionEventTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = ALWAYS,
    uses = {,}
)
@SuppressWarnings("unused")
public interface ExtAttractionEventTypeMapper {
    AttractionEventType createDtoToEntity(CreateAttractionEventTypeDTO createAttractionEventTypeDto);

    GetAttractionEventTypeDTO entityToGetDto(AttractionEventType attractionEventType);

    AttractionEventType updateEntity(UpdateAttractionEventTypeDTO updateAttractionEventTypeDto,
                                     @MappingTarget AttractionEventType attractionEventType);
}
