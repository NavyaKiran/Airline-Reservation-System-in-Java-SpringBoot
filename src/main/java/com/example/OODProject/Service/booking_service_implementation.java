package com.example.OODProject.Service;

import com.example.OODProject.DataAccess.booking_dataaccess;
import com.example.OODProject.DataAccess.schedule_dataaccess;
import com.example.OODProject.DataAccess.users_dataaccess;
import com.example.OODProject.Exception.AvailableRecordException;
import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.*;
import com.example.OODProject.Request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class booking_service_implementation implements booking_service{     //booking_services_implmn is adapter booking_services is target

    @Autowired
    booking_dataaccess bookingObj;

    @Autowired
    schedule_dataaccess scheduleObj;

    @Autowired
    users_dataaccess userObj;

    // Adapter patterns where the current class acts as adapter, booking_service as target and email service is adaptee
    email_services emailServiceRef;

    @Override
    public ResponseEntity<?> add_booking(BookingRequest bookingRequest) {
        try {
            Optional<Users> user = userObj.findById(bookingRequest.getEmail());
            if (!user.isPresent())
                throw new Exception("Invalid Email");

            Optional<Schedule> schedule = scheduleObj.findById(bookingRequest.getSchedule_id());

            if (! schedule.isPresent())
                throw new Exception("Invalid Schedule");

            if (schedule.get().getBookings() != null && !schedule.get().checkBookingOverflow())
                throw new Exception("Booking is full");

            Booking newBooking = new Booking();
            newBooking.setSchedule_id(schedule.get());
            newBooking.setEmail(user.get());
            bookingObj.save(newBooking);
            schedule.get().addBooking(newBooking.getBooking_id());

            scheduleObj.save(schedule.get());

            return new ResponseEntity<>(newBooking, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }



    }

    @Override
    public ResponseEntity<?> delete_booking(Integer booking_id) {

        try {
            Booking booking = bookingObj.getById(booking_id);
            if (booking == null)
                throw new Exception("Invalid booking_id, cannot delete");

            Schedule schedule = booking.getSchedule_id();
            schedule.removeBooking(booking.getBooking_id());

            bookingObj.delete(booking);
            scheduleObj.save(schedule);
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>("Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

//    @Override
//    public Booking view_specific_booking(int booking_id) {
////
////        try {
////            Booking booking = bookingObj.getById(booking_id);
////            if (booking == null)
////                throw new Exception("Invalid booking_id, cannot view");
////            return new ResponseEntity<>(booking, HttpStatus.OK);
////        } catch (Exception e){
////            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
////        }
//
//        Optional<Booking> findByBookingID = bookingObj.findById(booking_id);
//        if (findByBookingID.isPresent())
//            return findByBookingID.get();
//        else
//            throw new NotFoundException("The booking with Booking ID " + booking_id + " has not been found");
//    }

    @Override
    public ResponseEntity<?> view_specific_booking(int booking_id) {
        try {
            Booking findByBookingID = bookingObj.findById(booking_id).get();
            if (findByBookingID != null)
                return new ResponseEntity(findByBookingID, HttpStatus.OK);
            else
                throw new Exception("The booking with booking ID " + booking_id + " has not been found");
        }
        catch (Exception exception)
        {
//            System.out.println("navya");
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



    @Override
    public ResponseEntity<?> view_all() {
        try {
            List<Booking> bookings = bookingObj.findAll();
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    public void sendEmail(Users user){
        // send email code uses adaptee
        emailServiceRef.sendEmail(user);

        //
    }
}
