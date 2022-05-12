package com.example.OODProject.Service;

import com.example.OODProject.DataAccess.airport_dataaccess;
import com.example.OODProject.Exception.AvailableRecordException;
import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.Airport;
import com.example.OODProject.Model.UserRoles;
import com.example.OODProject.Model.Users;
import com.example.OODProject.Request.AirportRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class airport_services_implementation implements airport_services {
    @Autowired
    airport_dataaccess airportObj;

//    @Override
//    public ResponseEntity<?> add_airport(Airport airport) {
//        Optional<Airport> findByAirportCode = airportObj.findById(airport.getAirport_code());
//        try {
//            if (!findByAirportCode.isPresent()) {
//                airportObj.save(airport);
//                return new ResponseEntity<Airport>(airport, HttpStatus.OK);
//            } else
//                throw new AvailableRecordException("The airport with airport code " + airport.getAirport_code() + " is already available");
//        } catch (AvailableRecordException exception) {
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @Override
    public ResponseEntity<?> add_airport(AirportRequest request)
    {
        try {
            Optional<Airport> findByAirportCode= airportObj.findById(request.getAirport_code());
            if(findByAirportCode.isPresent())
            {
                throw new Exception("Airport with Airport code "+request.getAirport_code()+" is already present!");
            }
            else {
                    Airport airport = new Airport();
                    airport.setAirport_code(request.getAirport_code());
                    airport.setAirport_name(request.getAirport_name());
                    airport.setAirport_city(request.getAirport_city());
                    airport.setAirport_state(request.getAirport_state());
                    airport.setAirport_country(request.getAirport_country());
                    airportObj.save(airport);

                return new ResponseEntity<>(airport, HttpStatus.CREATED);
            }
        }
        catch(Exception exception)
        {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> modify_airport(Airport airport, String airport_code) {
        try {
            Airport airport_object = airportObj.findById(airport_code).get();
            if (airport_object == null)
                throw new NotFoundException("The airport with code " + airport_code + " has not been found");

            if (airport.getAirport_name() != null)
                airport_object.setAirport_name(airport.getAirport_name());
            if (airport.getAirport_city() != null)
                airport_object.setAirport_city(airport.getAirport_city());
            if (airport.getAirport_state() != null)
                airport_object.setAirport_state(airport.getAirport_state());
            if (airport.getAirport_country() != null)
                airport_object.setAirport_country(airport.getAirport_country());

            airportObj.save(airport_object);
            return new ResponseEntity<Airport>(airport_object, HttpStatus.OK);
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        }
    }


    @Override
    public ResponseEntity<?> delete_airport(String airport_code) {
        Optional<Airport> findByAirportCode = airportObj.findById(airport_code);
        if (findByAirportCode.isPresent()) {
            airportObj.deleteById(airport_code);
            return new ResponseEntity<>("The airport with airport code " + airport_code + "has been deleted", HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<>("The airport with airport code " + airport_code + "could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public Airport view_specific_airport(String airport_code) {
        Optional<Airport> findByAirportCode = airportObj.findById(airport_code);
        if (findByAirportCode.isPresent())
            return findByAirportCode.get();
        else
            throw new NotFoundException("The airport with airport code " + airport_code + " has not been found");
    }


//    @Override
//    public Iterable<Airport> view_all() {
//        return airportObj.findAll();
//

    @Override
    public ResponseEntity<?> view_all() {
        try {
            List<Airport> airports = airportObj.findAll();
            return new ResponseEntity<>(airports, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
