package com.pa.twb.service.mapper.ext;

import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.service.ext.dto.attractiongrouptype.CreateAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.GetAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.UpdateAttractionGroupTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = ALWAYS,
    uses = {,}
)
@SuppressWarnings("unused")
public interface ExtAttractionGroupTypeMapper {
    AttractionGroupType createDtoToEntity(CreateAttractionGroupTypeDTO createAttractionGroupTypeDto);

    GetAttractionGroupTypeDTO entityToGetDto(AttractionGroupType attractionGroupType);

    AttractionGroupType updateEntity(UpdateAttractionGroupTypeDTO updateAttractionGroupTypeDto,
                                     @MappingTarget AttractionGroupType attractionGroupType);
}
