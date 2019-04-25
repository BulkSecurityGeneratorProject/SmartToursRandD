package com.pa.twb.repository;

import com.pa.twb.domain.AttractionGroupType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AttractionGroupType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionGroupTypeRepository extends JpaRepository<AttractionGroupType, Long> {

}
