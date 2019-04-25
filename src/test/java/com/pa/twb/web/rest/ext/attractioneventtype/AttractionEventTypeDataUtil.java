package com.pa.twb.web.rest.ext.attractioneventtype;

import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.service.ext.dto.attractioneventtype.CreateAttractionEventTypeDTO;
import com.pa.twb.service.ext.dto.attractioneventtype.UpdateAttractionEventTypeDTO;
import javax.persistence.EntityManager;

public class AttractionEventTypeDataUtil {
    public static final String DEFAULT_NAME = "CCCCCCCCCC";

    public static final String UPDATED_NAME = "DDDDDDDDD";

    public static AttractionEventType createAttractionEventTypeEntity(EntityManager em,
                                                                      Object parent, Boolean deleted) {
        AttractionEventType entity = new AttractionEventType();
        // entity.setParent(parent)
        // entity.setDeleted(deleted)
        // entity.setName(DEFAULT_NAME)
        em.persist(entity);
        // parent.getEntities().add(entity)
        return entity;
    }

    public static AttractionEventType createAttractionEventTypeEntity(EntityManager em,
            Boolean deleted) {
        Object entity = new Object();
        return createAttractionEventTypeEntity(em, entity, deleted);
    }

    public static CreateAttractionEventTypeDTO createCreateAttractionEventTypeEntityDTO(Long parentId) {
        CreateAttractionEventTypeDTO createEntityDto = new CreateAttractionEventTypeDTO();
        // createEntityDto.setParentId(parentId)
        // createEntityDto.setName(DEFAULT_NAME)
        return createEntityDto;
    }

    public static UpdateAttractionEventTypeDTO createUpdateAttractionEventTypeEntityDTO(Long id) {
        UpdateAttractionEventTypeDTO updateEntityDto = new UpdateAttractionEventTypeDTO();
        updateEntityDto.setId(id);
        // updateEntityDto.setName(UPDATED_NAME)
        return updateEntityDto;
    }
}
