package com.example.OODProject.Service;

import com.example.OODProject.Model.Booking;
import com.example.OODProject.Model.Flight;
import com.example.OODProject.Request.BookingRequest;
import org.springframework.http.ResponseEntity;

public interface booking_service {
    //public ResponseEntity<?> modify_booking(Booking booking, int booking_id);
    public ResponseEntity<?> add_booking(BookingRequest bookingRequest);
    public ResponseEntity<?> delete_booking(Integer booking_id);
    public Booking  view_specific_booking(int booking_id);
    public ResponseEntity<?> view_all();
}
