//package com.example.OODProject.Controller;
//
//import com.example.OODProject.Exception.AvailableRecordException;
//import com.example.OODProject.Exception.NotFoundException;
//import com.example.OODProject.Model.Booking;
//import com.example.OODProject.Service.booking_service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@ComponentScan(basePackages = "com.example.OODProject")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RestController
//@RequestMapping("/api/booking")
//public class BookingController {
//
//    @Autowired
//    booking_service service;
//
//    @PostMapping("/add_booking")
//    @ExceptionHandler(AvailableRecordException.class)
//    public ResponseEntity<?> create_booking(@RequestBody Booking booking)
//    {
//        return service.add_booking(booking);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<?> delete_booking(@PathVariable("id") int booking_id)
//    {
//        return service.delete_booking(booking_id);
//    }
//
//    @GetMapping("/view")
//    public Iterable<Booking> view() {
//        return service.view_all();
//    }
//
////    @PutMapping("/modify_booking/{booking_id}")
////    @ExceptionHandler(NotFoundException.class)
////    public ResponseEntity update_booking(@PathVariable int booking_id,@RequestBody Booking booking) {
////        return service.modify_booking(booking, booking_id);
////
////    }
//
//    @GetMapping("/viewByBookingID/{id}")
//    @ExceptionHandler(NotFoundException.class)
//    public Booking viewByBookingID(@PathVariable("id") int booking_id)
//    {
//        return service.view_specific_booking(booking_id);
//    }
//
//
//}
