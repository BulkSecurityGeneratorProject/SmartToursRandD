package com.pa.twb.repository;

import com.pa.twb.domain.Attraction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Attraction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

}
