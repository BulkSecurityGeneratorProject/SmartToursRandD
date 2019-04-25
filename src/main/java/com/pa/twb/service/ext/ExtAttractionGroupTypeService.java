package com.pa.twb.service.ext;

import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.repository.AttractionGroupTypeRepository;
import com.pa.twb.repository.ext.ExtAttractionGroupTypeRepository;
import com.pa.twb.service.AttractionGroupTypeService;
import com.pa.twb.service.ext.dto.attractiongrouptype.CreateAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.GetAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.UpdateAttractionGroupTypeDTO;
import com.pa.twb.service.mapper.ext.ExtAttractionGroupTypeMapper;
import com.pa.twb.web.rest.errors.ext.AttractionGroupTypeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExtAttractionGroupTypeService extends AttractionGroupTypeService {
    private final ExtAttractionGroupTypeRepository extAttractionGroupTypeRepository;

    private final ExtAttractionGroupTypeMapper extAttractionGroupTypeMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ExtAttractionGroupTypeService(AttractionGroupTypeRepository attractionGroupTypeRepository,
                                         ExtAttractionGroupTypeRepository extAttractionGroupTypeRepository,
                                         ExtAttractionGroupTypeMapper extAttractionGroupTypeMapper) {
        super(attractionGroupTypeRepository);
        this.extAttractionGroupTypeRepository = extAttractionGroupTypeRepository;
        this.extAttractionGroupTypeMapper = extAttractionGroupTypeMapper;
    }

    public GetAttractionGroupTypeDTO create(CreateAttractionGroupTypeDTO createAttractionGroupTypeDto) {
        AttractionGroupType attractionGroupType = extAttractionGroupTypeMapper.createDtoToEntity(createAttractionGroupTypeDto);
        attractionGroupType = save(attractionGroupType);
        return extAttractionGroupTypeMapper.entityToGetDto(attractionGroupType);
    }

    public GetAttractionGroupTypeDTO update(UpdateAttractionGroupTypeDTO updateAttractionGroupTypeDto) {
        AttractionGroupType result = findByIdThrowException(updateAttractionGroupTypeDto.getId());
        result = extAttractionGroupTypeMapper.updateEntity(updateAttractionGroupTypeDto, result);
        return extAttractionGroupTypeMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public AttractionGroupType findByIdThrowException(Long id) {
        return extAttractionGroupTypeRepository.findById(id).orElseGet(() -> {
            throw new AttractionGroupTypeNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public GetAttractionGroupTypeDTO getById(Long id) {
        AttractionGroupType result = findByIdThrowException(id);
        return extAttractionGroupTypeMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public Page<GetAttractionGroupTypeDTO> getAll(Pageable pageable) {
        Page<AttractionGroupType> page = extAttractionGroupTypeRepository.findAll(pageable);
        return page.map(extAttractionGroupTypeMapper::entityToGetDto);
    }
}
