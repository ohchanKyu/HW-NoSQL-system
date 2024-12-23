package kr.ac.dankook.NoSQLProject.repository;

import kr.ac.dankook.NoSQLProject.document.Place;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
    List<Place> findByLocationNear(GeoJsonPoint location);
    Optional<Place> findByName(String name);
}
