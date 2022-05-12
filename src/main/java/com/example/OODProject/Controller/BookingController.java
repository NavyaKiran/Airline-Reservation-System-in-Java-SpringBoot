package com.example.OODProject.Controller;

import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.Booking;
import com.example.OODProject.Model.Schedule;
import com.example.OODProject.Request.BookingRequest;
import com.example.OODProject.Request.ScheduleRequest;
import com.example.OODProject.Service.booking_service;
import com.example.OODProject.Service.schedule_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan(basePackages = "com.example.OODProject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/booking")

public class BookingController {
    @Autowired
    booking_service service;

    @PostMapping("/create_booking")
    public ResponseEntity<?> add_booking(@RequestBody BookingRequest booking)
    {
        //System.out.println("Here");
        return service.add_booking(booking);
    }

    @DeleteMapping("/delete/{id}")
//    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> delete_booking(@PathVariable("id") Integer booking_id)
    {
        return service.delete_booking(booking_id);
    }

    @GetMapping("/view")
    public ResponseEntity<?> view() {
        return service.view_all();
    }

    @GetMapping("/viewByBookingID/{id}")
    @ExceptionHandler(NotFoundException.class)
    public Booking viewByScheduleID(@PathVariable("id") Integer booking_id)
    {
        return service.view_specific_booking(booking_id);
    }
}