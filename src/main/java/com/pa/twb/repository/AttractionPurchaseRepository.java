package com.pa.twb.repository;

import com.pa.twb.domain.AttractionPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the AttractionPurchase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttractionPurchaseRepository extends JpaRepository<AttractionPurchase, Long> {

    @Query(value = "select distinct attraction_purchase from AttractionPurchase attraction_purchase left join fetch attraction_purchase.groupTypes left join fetch attraction_purchase.eventTypes",
        countQuery = "select count(distinct attraction_purchase) from AttractionPurchase attraction_purchase")
    Page<AttractionPurchase> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct attraction_purchase from AttractionPurchase attraction_purchase left join fetch attraction_purchase.groupTypes left join fetch attraction_purchase.eventTypes")
    List<AttractionPurchase> findAllWithEagerRelationships();

    @Query("select attraction_purchase from AttractionPurchase attraction_purchase left join fetch attraction_purchase.groupTypes left join fetch attraction_purchase.eventTypes where attraction_purchase.id =:id")
    Optional<AttractionPurchase> findOneWithEagerRelationships(@Param("id") Long id);

}
