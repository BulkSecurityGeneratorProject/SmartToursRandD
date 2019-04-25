package com.pa.twb.repository.ext;

import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.repository.AttractionEventTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface ExtAttractionEventTypeRepository extends AttractionEventTypeRepository {
    @Override
    @Query("SELECT a " +
        "FROM AttractionEventType a " +
        "WHERE a.id = :id")
    Optional<AttractionEventType> findById(@Param("id") Long id);

    @Override
    @Query("SELECT a " +
        "FROM AttractionEventType a ")
    Page<AttractionEventType> findAll(Pageable pageable);

    @Override
    @Query("SELECT a " +
        "FROM AttractionEventType a ")
    List<AttractionEventType> findAll();
}
