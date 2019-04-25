package com.pa.twb.service.ext;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.AttractionPurchaseRepository;
import com.pa.twb.repository.ext.ExtAttractionPurchaseRepository;
import com.pa.twb.service.AttractionPurchaseService;
import com.pa.twb.service.ext.dto.attractionpurchase.CreateAttractionPurchaseDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.GetAttractionPurchaseDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.UpdateAttractionPurchaseDTO;
import com.pa.twb.service.mapper.ext.ExtAttractionPurchaseMapper;
import com.pa.twb.web.rest.errors.ext.AttractionPurchaseNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExtAttractionPurchaseService extends AttractionPurchaseService {
    private final ExtAttractionPurchaseRepository extAttractionPurchaseRepository;

    private final ExtAttractionPurchaseMapper extAttractionPurchaseMapper;

    private final ExtAttractionService extAttractionService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ExtAttractionPurchaseService(AttractionPurchaseRepository attractionPurchaseRepository,
                                        ExtAttractionPurchaseRepository extAttractionPurchaseRepository,
                                        ExtAttractionPurchaseMapper extAttractionPurchaseMapper,
                                        ExtAttractionService extAttractionService) {
        super(attractionPurchaseRepository);
        this.extAttractionPurchaseRepository = extAttractionPurchaseRepository;
        this.extAttractionPurchaseMapper = extAttractionPurchaseMapper;
        this.extAttractionService = extAttractionService;
    }

    public GetAttractionPurchaseDTO create(CreateAttractionPurchaseDTO createAttractionPurchaseDto) {
        AttractionPurchase attractionPurchase = extAttractionPurchaseMapper.createDtoToEntity(createAttractionPurchaseDto);
        attractionPurchase = save(attractionPurchase);
        return extAttractionPurchaseMapper.entityToGetDto(attractionPurchase);
    }

    public GetAttractionPurchaseDTO update(UpdateAttractionPurchaseDTO updateAttractionPurchaseDto) {
        AttractionPurchase result = findByIdThrowException(updateAttractionPurchaseDto.getId());
        result = extAttractionPurchaseMapper.updateEntity(updateAttractionPurchaseDto, result);
        return extAttractionPurchaseMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public AttractionPurchase findByIdThrowException(Long id) {
        return extAttractionPurchaseRepository.findById(id).orElseGet(() -> {
            throw new AttractionPurchaseNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public GetAttractionPurchaseDTO getById(Long id) {
        AttractionPurchase result = findByIdThrowException(id);
        return extAttractionPurchaseMapper.entityToGetDto(result);
    }

    @Transactional(readOnly = true)
    public Page<GetAttractionPurchaseDTO> getAll(Pageable pageable) {
        Page<AttractionPurchase> page = extAttractionPurchaseRepository.findAll(pageable);
        return page.map(extAttractionPurchaseMapper::entityToGetDto);
    }
}
