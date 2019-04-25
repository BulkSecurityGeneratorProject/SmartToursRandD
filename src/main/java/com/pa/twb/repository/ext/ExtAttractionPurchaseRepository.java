package com.pa.twb.repository.ext;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.AttractionPurchaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface ExtAttractionPurchaseRepository extends AttractionPurchaseRepository {
    @Override
    @Query("SELECT a " +
        "FROM AttractionPurchase a " +
        "WHERE a.id = :id")
    Optional<AttractionPurchase> findById(@Param("id") Long id);

    @Override
    @Query("SELECT a " +
        "FROM AttractionPurchase a ")
    Page<AttractionPurchase> findAll(Pageable pageable);

    @Override
    @Query("SELECT a " +
        "FROM AttractionPurchase a ")
    List<AttractionPurchase> findAll();

}
