package com.example.OODProject.DataAccess;

import com.example.OODProject.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface flight_dataaccess extends JpaRepository<Flight, Long> {
}
