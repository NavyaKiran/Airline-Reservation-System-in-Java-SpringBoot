package com.example.OODProject.DataAccess;

import com.example.OODProject.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface schedule_dataaccess extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT * FROM schedule s WHERE s.flight = :flight_number AND  " +
            " s.destination_airport = :destination_airport AND s.source_airport = :source_airport ", nativeQuery = true)
    Optional<Schedule> findByExistingSchedule(
            @Param("flight_number") long flight_number,
            @Param("source_airport") String source_airport,
            @Param("destination_airport") String destination_airport);
}
