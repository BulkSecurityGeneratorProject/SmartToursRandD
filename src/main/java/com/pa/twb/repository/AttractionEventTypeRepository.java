package com.pa.twb.repository;

import com.pa.twb.domain.AttractionEventType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AttractionEventType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionEventTypeRepository extends JpaRepository<AttractionEventType, Long> {

}
