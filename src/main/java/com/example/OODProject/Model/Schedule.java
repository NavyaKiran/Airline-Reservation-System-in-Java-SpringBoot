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
    //HashSet<Long> bookings = new HashSet<>();
    private HashSet<Integer> bookings = new HashSet<Integer>();
    private double price;

    public void addBooking(Integer booking){
        this.bookings.add(booking);
    }

    public void removeBooking(Integer booking){
        this.bookings.remove(booking);
    }

    public boolean checkBookingOverflow(){
//        System.out.println(this.bookings.size() + " _ " + this.flight.getNumber_of_seats());
        return this.bookings.size() < this.flight.getNumber_of_seats();
    }

//    public boolean checkSeat(Flight flight){
//        return flight.getNumber_of_seats() >= this.bookings.size();
//    }
}
