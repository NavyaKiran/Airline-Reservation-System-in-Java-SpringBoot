package com.example.OODProject.Service;

import com.example.OODProject.Model.Flight;
import com.example.OODProject.Request.FlightRequest;
import org.springframework.http.ResponseEntity;

public interface flight_services {
    public ResponseEntity<?> add_flight(Flight flight);
    public ResponseEntity<?> modify_flight(Flight flight, Long flight_number);
    public ResponseEntity<?> delete_flight(Long flight_number);
    public ResponseEntity<?> view_specific_flight(Long flight_number);
    public ResponseEntity<?> view_all();
}
