package com.pa.twb.repository.ext;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.AttractionRepository;
import com.pa.twb.service.ext.processing.dto.location.GetEntityWithLocationDTO;
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
        "WHERE a.id = :id ")
    Optional<Attraction> findById(@Param("id") Long id);

    @Query("SELECT a " +
        "FROM Attraction a " +
        "ORDER BY FUNCTION('RAND')")
    List<Attraction> findAllRandomOrder();

    @Query(value = "SELECT " +
        "    attraction.id                                         AS id, " +
        "    (6371 * acos(cos(radians(?2)) " +
        "                 * cos(radians(attraction.longitude)) " +
        "                 * cos(radians(attraction.latitude) - radians(?1)) " +
        "                 + sin(radians(?2)) " +
        "                   * sin(radians(attraction.longitude)))) AS distance " +
        "FROM attraction attraction " +
        "ORDER BY distance",
        name = "GetEntityWithLocationDTO",
        countQuery = "SELECT COUNT(*) FROM attraction",
        nativeQuery = true)
    Page<GetEntityWithLocationDTO> findByDistance(Double latitude, Double longitude, Pageable pageable);
}
