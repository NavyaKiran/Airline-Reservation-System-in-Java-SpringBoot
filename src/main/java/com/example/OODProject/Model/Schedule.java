package com.example.OODProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long schedule_id;

    @ManyToOne
    @JoinColumn(name="flight", referencedColumnName = "flight_number")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name="source_airport", referencedColumnName = "airport_code")
    private Airport source_airport;

    @ManyToOne
    @JoinColumn(name="destination_airport", referencedColumnName = "airport_code")
    private Airport destination_airport;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus ScheduleStatus;

    Instant scheduled_on;
    HashSet<Booking> bookings = new HashSet<>();
    private double price;

//    public boolean checkSeat(Flight flight){
//        return flight.getNumber_of_seats() >= this.bookings.size();
//    }
}
