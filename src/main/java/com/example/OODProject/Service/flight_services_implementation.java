package com.example.OODProject.Service;

import com.example.OODProject.DataAccess.flight_dataaccess;
import com.example.OODProject.Exception.AvailableRecordException;
import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.*;
import com.example.OODProject.Request.FlightRequest;
import com.example.OODProject.Request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class flight_services_implementation implements flight_services {

    @Autowired
    flight_dataaccess flightObj;


    @Override
    public ResponseEntity<?> add_flight(FlightRequest request) {
        try {
            Optional<Flight> findByFlightNumber = flightObj.findById(request.getFlight_number());
            if(findByFlightNumber.isPresent())
            {
                throw new Exception("Flight with flight number "+request.getFlight_number()+" is already present");
            }
            else {
               Flight flight = new Flight();
               flight.setFlight_number(request.getFlight_number());
               flight.setAirline(request.getAirline());
               flight.setNumber_of_seats(request.getNumber_of_seats());
               if(request.getFlight_type().toString().toUpperCase() == Flight_type.AIRBUS.toString())
                   flight.setFlight_type(Flight_type.AIRBUS);
               else
                   flight.setFlight_type(Flight_type.BOEING);

               flightObj.save(flight);

                return new ResponseEntity<>(flight, HttpStatus.CREATED);
            }
        }
        catch(Exception exception)
        {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> modify_flight(Flight flight, Long flight_number) {
        try {
            Flight flight_object = flightObj.findById(flight_number).get();
            if (flight_object == null)
                throw new NotFoundException("The flight with number " + flight_number + " has not been found");

            if (flight.getAirline() != null)
                flight_object.setAirline(flight.getAirline());

            if (!Objects.isNull(flight.getNumber_of_seats()) && flight.getNumber_of_seats() != 0)
                flight_object.setNumber_of_seats(flight.getNumber_of_seats());

            flightObj.save(flight_object);
            return new ResponseEntity<Flight>(flight_object, HttpStatus.OK);
        }
        catch(NotFoundException exception)
        {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> delete_flight(Long flight_number) {
        try {
            Optional<Flight> findByFlightNumber = flightObj.findById(flight_number);
            if (findByFlightNumber.isPresent()) {
                flightObj.deleteById(flight_number);
                return new ResponseEntity<>("The flight with flight number " + flight_number + " has been deleted", HttpStatus.ACCEPTED);
            } else
                throw new Exception("The flight with flight number " + flight_number + "could not be deleted");
        }
        catch(Exception exception)
        {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<?> view_specific_flight(Long flight_number) {
        try {
            Flight findByFlightNumber = flightObj.findById(flight_number).get();
//            System.out.println("DD");
//            System.out.println(findByFlightNumber);
            if (findByFlightNumber != null)
                return new ResponseEntity(findByFlightNumber, HttpStatus.OK);
            else
                throw new NotFoundException("The flight with flight number " + flight_number + " has not been found");
        } catch (Exception exception) {
                return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @Override
//    public Iterable<Flight> view_all() {
//        return flightObj.findAll();
//    }
public ResponseEntity<?> view_all() {
    try {
        List<Flight> flight = flightObj.findAll();
        return new ResponseEntity<>(flight, HttpStatus.OK);
    } catch (Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
}
