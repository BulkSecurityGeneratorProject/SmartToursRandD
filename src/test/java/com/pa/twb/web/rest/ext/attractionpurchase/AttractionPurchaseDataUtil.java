package com.pa.twb.web.rest.ext.attractionpurchase;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.service.ext.dto.attractionpurchase.RegisterInterestDTO;
import com.pa.twb.service.ext.dto.attractionpurchase.UpdateAttractionPurchaseDTO;
import javax.persistence.EntityManager;

public class AttractionPurchaseDataUtil {
    public static final String DEFAULT_NAME = "CCCCCCCCCC";

    public static final String UPDATED_NAME = "DDDDDDDDD";

    public static AttractionPurchase createAttractionPurchaseEntity(EntityManager em, Object parent,
                                                                    Boolean deleted) {
        AttractionPurchase entity = new AttractionPurchase();
        // entity.setParent(parent)
        // entity.setDeleted(deleted)
        // entity.setName(DEFAULT_NAME)
        em.persist(entity);
        // parent.getEntities().add(entity)
        return entity;
    }

    public static AttractionPurchase createAttractionPurchaseEntity(EntityManager em,
            Boolean deleted) {
        Object entity = new Object();
        return createAttractionPurchaseEntity(em, entity, deleted);
    }

    public static RegisterInterestDTO createCreateAttractionPurchaseEntityDTO(Long parentId) {
        RegisterInterestDTO createEntityDto = new RegisterInterestDTO();
        // createEntityDto.setParentId(parentId)
        // createEntityDto.setName(DEFAULT_NAME)
        return createEntityDto;
    }

    public static UpdateAttractionPurchaseDTO createUpdateAttractionPurchaseEntityDTO(Long id) {
        UpdateAttractionPurchaseDTO updateEntityDto = new UpdateAttractionPurchaseDTO();
        updateEntityDto.setId(id);
        // updateEntityDto.setName(UPDATED_NAME)
        return updateEntityDto;
    }
}
