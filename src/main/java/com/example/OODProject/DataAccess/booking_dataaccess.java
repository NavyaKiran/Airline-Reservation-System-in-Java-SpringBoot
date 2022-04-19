package com.example.OODProject.DataAccess;

import com.example.OODProject.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface booking_dataaccess extends JpaRepository<Booking, Integer> {
}
