package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Page<Property> findByValueGreaterThanEqual(double minValue, Pageable pageable);

    Page<Property> findByPropertyTypeIgnoreCase(String type, Pageable pageable);

    Page<Property> findBySizeInSqFtBetween(double min, double max, Pageable pageable);

    Page<Property> findByOwnerNameContainingIgnoreCase(String name, Pageable pageable);

//    Page<Property> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng, Pageable pageable);

    @Query(value = "SELECT * FROM property p WHERE " +
            "ST_Y(p.location) BETWEEN :minLat AND :maxLat AND " +
            "ST_X(p.location) BETWEEN :minLng AND :maxLng",
            nativeQuery = true)
    Page<Property> findByLocationWithinBounds(
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLng") double minLng,
            @Param("maxLng") double maxLng,
            Pageable pageable);

    @Query(value = "SELECT * FROM property p WHERE ST_DWithin(p.location, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)::geography, :radius)", nativeQuery = true)
    Page<Property> findPropertiesWithinDistance(@Param("lat") double lat,
                                                @Param("lng") double lng,
                                                @Param("radius") double radiusInMeters,
                                                Pageable pageable);




    @Query(value = """
    SELECT 
        ST_X(ST_Centroid(ST_Collect(p.location))) AS lon,
        ST_Y(ST_Centroid(ST_Collect(p.location))) AS lat,
        COUNT(*) AS count,
        AVG(p.value) AS avg_value,
        AVG(p.estimated_net_worth) AS avg_net_worth
    FROM property p
    WHERE ST_DWithin(p.location, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), :radius)
    GROUP BY ST_SnapToGrid(p.location, :gridSize)
    """, nativeQuery = true)
    List<Object[]> clusterProperties(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("radius") double radius,
            @Param("gridSize") double gridSize
    );

    @Query(value = "SELECT * FROM property p WHERE ST_Within(p.location, ST_GeomFromText(:polygon, 4326))", nativeQuery = true)
    List<Property> findWithinPolygon(@Param("polygon") String polygonWKT);

    @Query(value = "SELECT * FROM property WHERE ST_Within(location, ST_GeomFromText(:wkt, 4326))", nativeQuery = true)
    List<Property> findByPolygonWKT(@Param("wkt") String wkt);

    @Query(value = """
    SELECT *, ST_Distance(location, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)) AS distance
    FROM property
    ORDER BY location <-> ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)
    LIMIT :limit
    """, nativeQuery = true)
    List<Property> findPropertiesSortedByDistance(@Param("lat") double lat, @Param("lng") double lng, @Param("limit") int limit);

}
