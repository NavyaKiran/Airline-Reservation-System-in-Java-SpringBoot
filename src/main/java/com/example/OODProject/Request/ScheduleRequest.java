package com.example.OODProject.Request;

import com.example.OODProject.Model.Airport;
import com.example.OODProject.Model.Booking;
import com.example.OODProject.Model.Flight;
import com.example.OODProject.Model.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ScheduleRequest {
    private long schedule_id;
    private long flight_number;
    private String source_airport;
    private String destination_airport;
    private ScheduleStatus ScheduleStatus;
    private Instant scheduled_on;
    private double price;
}
