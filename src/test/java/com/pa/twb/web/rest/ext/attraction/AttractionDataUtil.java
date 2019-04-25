package com.pa.twb.web.rest.ext.attraction;

import com.pa.twb.domain.Attraction;
import com.pa.twb.service.ext.dto.attraction.CreateAttractionDTO;
import com.pa.twb.service.ext.dto.attraction.UpdateAttractionDTO;
import javax.persistence.EntityManager;

public class AttractionDataUtil {
    public static final String DEFAULT_NAME = "CCCCCCCCCC";

    public static final String UPDATED_NAME = "DDDDDDDDD";

    public static Attraction createAttractionEntity(EntityManager em, Object parent,
                                                    Boolean deleted) {
        Attraction entity = new Attraction();
        // entity.setParent(parent)
        // entity.setDeleted(deleted)
        // entity.setName(DEFAULT_NAME)
        em.persist(entity);
        // parent.getEntities().add(entity)
        return entity;
    }

    public static Attraction createAttractionEntity(EntityManager em, Boolean deleted) {
        Object entity = new Object();
        return createAttractionEntity(em, entity, deleted);
    }

    public static CreateAttractionDTO createCreateAttractionEntityDTO(Long parentId) {
        CreateAttractionDTO createEntityDto = new CreateAttractionDTO();
        // createEntityDto.setParentId(parentId)
        // createEntityDto.setName(DEFAULT_NAME)
        return createEntityDto;
    }

    public static UpdateAttractionDTO createUpdateAttractionEntityDTO(Long id) {
        UpdateAttractionDTO updateEntityDto = new UpdateAttractionDTO();
        updateEntityDto.setId(id);
        // updateEntityDto.setName(UPDATED_NAME)
        return updateEntityDto;
    }
}
