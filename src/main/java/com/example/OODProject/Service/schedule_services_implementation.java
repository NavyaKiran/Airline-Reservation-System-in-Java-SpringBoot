package com.example.OODProject.Service;

import com.example.OODProject.DataAccess.airport_dataaccess;
import com.example.OODProject.DataAccess.flight_dataaccess;
import com.example.OODProject.DataAccess.schedule_dataaccess;
import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.*;
import com.example.OODProject.Request.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class schedule_services_implementation implements schedule_services {

    @Autowired
    schedule_dataaccess scheduleObj;


    @Autowired
    flight_dataaccess flightObj;

    @Autowired
    airport_dataaccess airportObj;

    @Override
    public ResponseEntity<?> add_schedule(ScheduleRequest scheduleRequest) {
        try {
            // validate flight
            Optional<Flight> _flight = flightObj.findById(scheduleRequest.getFlight_number());
            if (!_flight.isPresent()) {
                throw new Error(" The flight number is invalid");
            }
            Flight flight = flightObj.findById(scheduleRequest.getFlight_number()).get();
            if (scheduleRequest.getSource_airport() == null || scheduleRequest.getDestination_airport() == null) {
                throw new Error("The source and destination airports are not valid");
            }
            Airport srcAirport = airportObj.findByAirportCode(scheduleRequest.getSource_airport());
            Airport dstAirport = airportObj.findByAirportCode(scheduleRequest.getDestination_airport());
            if (srcAirport == null || dstAirport == null) {
                throw new Error("The airports are not valid!");
            }
            if (srcAirport == dstAirport) {
                throw new Error("The source and destination airports cannot be the same");
            }

            // check schedule if there exist the same flight with src dst and flight_no
            Optional<Schedule> checkSchedule = scheduleObj.findByExistingSchedule(scheduleRequest.getFlight_number(), srcAirport.getAirport_code(), dstAirport.getAirport_code());
            if (checkSchedule.isPresent()) {
                throw new Error("A similar schedule already exists.");
            }

            //  validate price
            if (scheduleRequest.getPrice() == 0) {
                throw new Error("The fare is invalid!");
            }

            Schedule schedule = new Schedule();
            schedule.setScheduleStatus(ScheduleStatus.ON_TIME);
            schedule.setFlight(flight);
            schedule.setSource_airport(srcAirport);
            schedule.setDestination_airport(dstAirport);
            schedule.setPrice(scheduleRequest.getPrice());
            scheduleObj.save(schedule);

            return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in creating schedule" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<?> delete_schedule(Long schedule_id) {
        try {
            Optional<Schedule> findByScheduleID = scheduleObj.findById(schedule_id);
            if (findByScheduleID.isPresent()) {
                scheduleObj.deleteById(schedule_id);
                return new ResponseEntity<>("The schedule with schedule ID " + schedule_id + " has been deleted", HttpStatus.ACCEPTED);
            } else
                throw new Exception("The schedule with schedule ID " + schedule_id + "could not be deleted");
        }
        catch(Exception exception)
        {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


//    @Override
//    public Schedule view_specific_schedule(Long schedule_id) {
//        Optional<Schedule> findByScheduleID = scheduleObj.findById(schedule_id);
//        if (findByScheduleID.isPresent())
//            return findByScheduleID.get();
//        else
//            throw new NotFoundException("The schedule with ID " + schedule_id + " has not been found");
//    }

    @Override
    public ResponseEntity<?> view_specific_schedule(Long schedule_id) {
        try {
            Schedule findByScheduleID = scheduleObj.findById(schedule_id).get();
            if (findByScheduleID != null)
                return new ResponseEntity(findByScheduleID, HttpStatus.OK);
            else
                throw new Exception(" The Schedule with ID" + schedule_id + " has not been found");
        }
        catch (Exception exception)
        {
//            System.out.println("navya");
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> view_all() {
        try {
            List<Schedule> schedule = scheduleObj.findAll();
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
