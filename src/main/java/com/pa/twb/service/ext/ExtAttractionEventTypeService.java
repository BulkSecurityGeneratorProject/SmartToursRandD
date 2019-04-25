package com.pa.twb.service.ext;

import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.repository.AttractionEventTypeRepository;
import com.pa.twb.repository.ext.ExtAttractionEventTypeRepository;
import com.pa.twb.service.AttractionEventTypeService;
import com.pa.twb.service.ext.dto.attractioneventtype.CreateAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.GetAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.UpdateAttractionEventTypeDTO;
import com.pa.twb.service.mapper.ext.ExtAttractionEventTypeMapper;
import com.pa.twb.web.rest.errors.ext.AttractionEventTypeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExtAttractionEventTypeService extends AttractionEventTypeService {
    private final ExtAttractionEventTypeRepository extAttractionEventTypeRepository;

    private final ExtAttractionEventTypeMapper extAttractionEventTypeMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ExtAttractionEventTypeService(AttractionEventTypeRepository attractionEventTypeRepository,
                                         ExtAttractionEventTypeRepository extAttractionEventTypeRepository,
                                         ExtAttractionEventTypeMapper extAttractionEventTypeMapper) {
        super(attractionEventTypeRepository);
        this.extAttractionEventTypeRepository = extAttractionEventTypeRepository;
        this.extAttractionEventTypeMapper = extAttractionEventTypeMapper;
    }

    public GetAttractionEventTypeDTO create(CreateAttractionEventTypeDTO createAttractionEventTypeDto) {
        AttractionEventType attractionEventType = extAttractionEventTypeMapper.createDtoToEntity(createAttractionEventTypeDto);
        attractionEventType = save(attractionEventType);
        return extAttractionEventTypeMapper.entityToGetDto(attractionEventType);
    }

    public GetAttractionEventTypeDTO update(UpdateAttractionEventTypeDTO updateAttractionEventTypeDto) {
        AttractionEventType result = findByIdThrowException(updateAttractionEventTypeDto.getId());
        result = extAttractionEventTypeMapper.updateEntity(updateAttractionEventTypeDto, result);
        return extAttractionEventTypeMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public AttractionEventType findByIdThrowException(Long id) {
        return extAttractionEventTypeRepository.findById(id).orElseGet(() -> {
            throw new AttractionEventTypeNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public GetAttractionEventTypeDTO getById(Long id) {
        AttractionEventType result = findByIdThrowException(id);
        return extAttractionEventTypeMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public Page<GetAttractionEventTypeDTO> getAll(Pageable pageable) {
        Page<AttractionEventType> page = extAttractionEventTypeRepository.findAll(pageable);
        return page.map(extAttractionEventTypeMapper::entityToGetDto);
    }
}
