package com.pa.twb.repository.ext;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.AttractionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface ExtAttractionRepository extends AttractionRepository {
    @Override
    @Query("SELECT a " +
        "FROM Attraction a " +
        "WHERE a.id = :id " +
        "AND a.lat IS NOT NULL " +
        "AND a.lng IS NOT NULL")
    Optional<Attraction> findById(@Param("id") Long id);

    @Override
    @Query("SELECT a " +
        "FROM Attraction a ")
    Page<Attraction> findAll(Pageable pageable);

    @Override
    @Query("SELECT a " +
        "FROM Attraction a ")
    List<Attraction> findAll();

}
