package com.pa.twb.repository.ext;

import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.repository.AttractionGroupTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public interface ExtAttractionGroupTypeRepository extends AttractionGroupTypeRepository {
    @Override
    @Query("SELECT a " +
        "FROM AttractionGroupType a " +
        "WHERE a.id = :id")
    Optional<AttractionGroupType> findById(@Param("id") Long id);

    @Override
    @Query("SELECT a " +
        "FROM AttractionGroupType a ")
    Page<AttractionGroupType> findAll(Pageable pageable);

    @Override
    @Query("SELECT a " +
        "FROM AttractionGroupType a ")
    List<AttractionGroupType> findAll();
}
