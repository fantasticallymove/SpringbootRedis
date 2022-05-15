package com.example.redishash.test.redishash.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM city c WHERE city_name = ?1")
    City getFromAndToCity(String from);
}
