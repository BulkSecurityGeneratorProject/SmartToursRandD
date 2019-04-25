package com.pa.twb.repository;

import com.pa.twb.domain.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Attraction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    @Query(value = "select distinct attraction from Attraction attraction left join fetch attraction.groupTypes left join fetch attraction.eventTypes",
        countQuery = "select count(distinct attraction) from Attraction attraction")
    Page<Attraction> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct attraction from Attraction attraction left join fetch attraction.groupTypes left join fetch attraction.eventTypes")
    List<Attraction> findAllWithEagerRelationships();

    @Query("select attraction from Attraction attraction left join fetch attraction.groupTypes left join fetch attraction.eventTypes where attraction.id =:id")
    Optional<Attraction> findOneWithEagerRelationships(@Param("id") Long id);

}
