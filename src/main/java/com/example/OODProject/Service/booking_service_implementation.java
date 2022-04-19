//package com.example.OODProject.Service;
//
//import com.example.OODProject.DataAccess.booking_dataaccess;
//import com.example.OODProject.Exception.AvailableRecordException;
//import com.example.OODProject.Exception.NotFoundException;
//import com.example.OODProject.Model.Booking;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Objects;
//import java.util.Optional;
//
//@Transactional
//@Service
//public class booking_service_implementation implements booking_service{
//
//    @Autowired
//    booking_dataaccess bookingObj;
//
//    @Override
//    public ResponseEntity<?> add_booking(Booking booking) {
//        Optional<Booking> findByBookingID = bookingObj.findById(booking.getBooking_id());
//        try {
//            if(!findByBookingID.isPresent())
//            {
//                bookingObj.save(booking);
//                return new ResponseEntity<>(booking, HttpStatus.OK);
//            }
//            else
//                throw new AvailableRecordException("The booking with booking ID "+booking.getBooking_id()+
//                        " is already available");
//        }
//        catch(AvailableRecordException exception)
//        {
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
////    @Override
////    public ResponseEntity<?> modify_booking(Booking booking, int booking_id) {
////        try {
////            Booking booking_object = bookingObj.findById(booking_id).get();
////            if (booking_object == null)
////                throw new NotFoundException("The booking with booking ID " + booking_id + " has not been found");
////
////            if (booking.getEmail() != null)
////                booking_object.set(booking.getBooking_date());
////
////            if (!Objects.isNull(booking.getCount_of_passengers()) && booking.getCount_of_passengers() != 0)
////                booking_object.setCount_of_passengers(booking.getCount_of_passengers());
////
////            bookingObj.save(booking_object);
////            return new ResponseEntity<Booking>(booking_object, HttpStatus.OK);
////        }
////
////        catch(NotFoundException exception)
////        {
////            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
////
////        }
////    }
//
//    @Override
//    public ResponseEntity<?> delete_booking(int booking_id) {
//        Optional<Booking> findByBookingID = bookingObj.findById(booking_id);
//        if(findByBookingID.isPresent())
//        {
//            bookingObj.deleteById(booking_id);
//            return new ResponseEntity<>("The booking with booking ID " + booking_id +
//                    "has been deleted", HttpStatus.ACCEPTED);
//        }
//        else
//            return new  ResponseEntity<>("The booking with booking ID " +booking_id
//                    + "could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public Booking view_specific_booking(int booking_id) {
//        Optional<Booking> findByBookingID = bookingObj.findById(booking_id);
//        if (findByBookingID.isPresent())
//            return findByBookingID.get();
//        else
//            throw new NotFoundException("The booking with booking ID " +booking_id+ " has not been found");
//    }
//
//    @Override
//    public Iterable<Booking> view_all() {
//        return bookingObj.findAll();
//    }
//}
