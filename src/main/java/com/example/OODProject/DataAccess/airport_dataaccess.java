package com.example.OODProject.DataAccess;

import com.example.OODProject.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface airport_dataaccess extends JpaRepository<Airport, String> {

    @Query(value = "SELECT * FROM airport WHERE airport_code = :source_airport", nativeQuery = true)
    Airport findByAirportCode(@Param("source_airport") String source_airport);
}
