package com.pa.twb.service.mapper.ext;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.service.ext.dto.attractionpurchase.RegisterInterestDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.GetAttractionPurchaseDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.UpdateAttractionPurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = ALWAYS,
    uses = {,}
)
@SuppressWarnings("unused")
public interface ExtAttractionPurchaseMapper {
    AttractionPurchase createDtoToEntity(RegisterInterestDTO registerInterestDTO);

    GetAttractionPurchaseDTO entityToGetDto(AttractionPurchase attractionPurchase);

    AttractionPurchase updateEntity(UpdateAttractionPurchaseDTO updateAttractionPurchaseDto,
                                    @MappingTarget AttractionPurchase attractionPurchase);
}
