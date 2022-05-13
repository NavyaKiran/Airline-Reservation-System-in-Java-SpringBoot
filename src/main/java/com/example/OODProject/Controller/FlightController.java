package com.example.OODProject.Controller;


import com.example.OODProject.Exception.AvailableRecordException;
import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.Flight;
import com.example.OODProject.Service.flight_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan(basePackages = "com.example.OODProject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    flight_services service;

    @PostMapping("/add_flight")
    @ExceptionHandler(AvailableRecordException.class)
    public ResponseEntity<?> create_flight(@RequestBody Flight flight)
    {
        return service.add_flight(flight);
    }

    @DeleteMapping("/delete/{id}")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> delete_flight(@PathVariable("id") Long flight_number)
    {
        return service.delete_flight(flight_number);
    }

    @PutMapping("/modify_flight/{flight_number}")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity update_flight(@PathVariable Long flight_number,@RequestBody Flight flight) {
        return service.modify_flight(flight, flight_number);

    }

    @GetMapping("/view")
    public ResponseEntity<?> view() {
        return service.view_all();
    }

    @GetMapping("/viewByFlightNumber/{id}")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> viewByFlightNumber(@PathVariable("id") Long flight_number)
    {
        return service.view_specific_flight(flight_number);
    }
}

