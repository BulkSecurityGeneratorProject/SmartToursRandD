package com.pa.twb.web.rest.ext.attractiongrouptype;

import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.service.ext.dto.attractiongrouptype.CreateAttractionGroupTypeDTO;
import com.pa.twb.service.ext.dto.attractiongrouptype.UpdateAttractionGroupTypeDTO;
import javax.persistence.EntityManager;

public class AttractionGroupTypeDataUtil {
    public static final String DEFAULT_NAME = "CCCCCCCCCC";

    public static final String UPDATED_NAME = "DDDDDDDDD";

    public static AttractionGroupType createAttractionGroupTypeEntity(EntityManager em,
                                                                      Object parent, Boolean deleted) {
        AttractionGroupType entity = new AttractionGroupType();
        // entity.setParent(parent)
        // entity.setDeleted(deleted)
        // entity.setName(DEFAULT_NAME)
        em.persist(entity);
        // parent.getEntities().add(entity)
        return entity;
    }

    public static AttractionGroupType createAttractionGroupTypeEntity(EntityManager em,
            Boolean deleted) {
        Object entity = new Object();
        return createAttractionGroupTypeEntity(em, entity, deleted);
    }

    public static CreateAttractionGroupTypeDTO createCreateAttractionGroupTypeEntityDTO(Long parentId) {
        CreateAttractionGroupTypeDTO createEntityDto = new CreateAttractionGroupTypeDTO();
        // createEntityDto.setParentId(parentId)
        // createEntityDto.setName(DEFAULT_NAME)
        return createEntityDto;
    }

    public static UpdateAttractionGroupTypeDTO createUpdateAttractionGroupTypeEntityDTO(Long id) {
        UpdateAttractionGroupTypeDTO updateEntityDto = new UpdateAttractionGroupTypeDTO();
        updateEntityDto.setId(id);
        // updateEntityDto.setName(UPDATED_NAME)
        return updateEntityDto;
    }
}
