package com.pa.twb.repository;

import com.pa.twb.domain.AttractionPurchase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AttractionPurchase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionPurchaseRepository extends JpaRepository<AttractionPurchase, Long> {

}
