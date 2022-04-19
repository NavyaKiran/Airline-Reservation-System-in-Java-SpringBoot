package com.example.OODProject.Service;

import com.example.OODProject.DataAccess.airport_dataaccess;
import com.example.OODProject.DataAccess.flight_dataaccess;
import com.example.OODProject.DataAccess.schedule_dataaccess;
import com.example.OODProject.Model.Airport;
import com.example.OODProject.Model.Flight;
import com.example.OODProject.Model.Schedule;
import com.example.OODProject.Model.ScheduleStatus;
import com.example.OODProject.Request.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class schedule_services_implementation implements schedule_services {

    @Autowired
    schedule_dataaccess scheduleObj;


    @Autowired
    flight_dataaccess flightObj ;

    @Autowired
    airport_dataaccess airportObj ;

    @Override
    public ResponseEntity<?> add_schedule(ScheduleRequest scheduleRequest) {
        try{
            // validate flight
            Optional<Flight> _flight = flightObj.findById(scheduleRequest.getFlight_number());
            if (!_flight.isPresent()){
                throw new Error("Flight number is invalid");
            }
            Flight flight = flightObj.findById(scheduleRequest.getFlight_number()).get();
            if(scheduleRequest.getSource_airport() == null || scheduleRequest.getDestination_airport() == null){
                throw new Error("Src or destination Airports are not valid");
            }
            Airport srcAirport = airportObj.findByAirportCode(scheduleRequest.getSource_airport());
            Airport dstAirport = airportObj.findByAirportCode(scheduleRequest.getDestination_airport());
            if(srcAirport == null || dstAirport == null){
                throw new Error("Airports are not valid");
            }
            if(srcAirport == dstAirport){
                throw new Error("Source and destination airports cannot be same");
            }

            // check schedule if there exist the same flight with src dst and flight_no
            Optional<Schedule> checkSchedule = scheduleObj.findByExistingSchedule(scheduleRequest.getFlight_number(), srcAirport.getAirport_code(), dstAirport.getAirport_code());
            if (checkSchedule.isPresent()){
                throw new Error("Similar schedule already exist");
            }

            //  validate price
            if(scheduleRequest.getPrice() == 0){
                throw  new Error("Invalid fare");
            }

            Schedule schedule = new Schedule();
            schedule.setScheduleStatus(ScheduleStatus.ON_TIME);
            schedule.setFlight(flight);
            schedule.setSource_airport(srcAirport);
            schedule.setDestination_airport(dstAirport);
            schedule.setPrice(scheduleRequest.getPrice());
            scheduleObj.save(schedule);

            return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error in creating schedule" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public ResponseEntity<?> modify_schedule(Schedule schedule, Long schedule_id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> delete_schedule(Long schedule_id) {
//        return null;
//    }
//
//    @Override
//    public Flight view_specific_schedule(Long schedule_id) {
//        return null;
//    }
//
//    @Override
//    public Iterable<Flight> view_all() {
//        return null;
//    }
}
