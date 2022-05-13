package com.example.OODProject.Service;

import com.example.OODProject.DataAccess.flight_dataaccess;
import com.example.OODProject.Exception.AvailableRecordException;
import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.Booking;
import com.example.OODProject.Model.Flight;
import com.example.OODProject.Request.FlightRequest;
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
    public ResponseEntity<?> add_flight(Flight flight) {
        Optional<Flight> findByFlightNumber = flightObj.findById(flight.getFlight_number());
        try {
            if(!findByFlightNumber.isPresent())
            {
                flightObj.save(flight);
                return new ResponseEntity<>(flight, HttpStatus.OK);
            }
            else
                throw new AvailableRecordException("The flight with flight number "+flight.getFlight_number()+" is already available");
        }
        catch(AvailableRecordException exception)
        {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
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
        Optional<Flight> findByFlightNumber = flightObj.findById(flight_number);
        if(findByFlightNumber.isPresent())
        {
            flightObj.deleteById(flight_number);
            return new ResponseEntity<>("The flight with flight number " + flight_number + "has been deleted", HttpStatus.ACCEPTED);
        }
        else
            return new  ResponseEntity<>("The flight with flight number " +flight_number+ "could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity view_specific_flight(Long flight_number) {
        try {
            Flight findByFlightNumber = flightObj.findById(flight_number).get();
//            System.out.println("DD");
//            System.out.println(findByFlightNumber);
            if (findByFlightNumber != null)
                return new ResponseEntity(findByFlightNumber, HttpStatus.OK);
            else
                throw new NotFoundException("The flight with flight number " + flight_number + " has not been found");
        } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
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
