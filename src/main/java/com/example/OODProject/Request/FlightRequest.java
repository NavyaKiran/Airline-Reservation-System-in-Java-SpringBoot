package com.example.OODProject.Request;

import com.example.OODProject.Model.Flight_type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
public class FlightRequest {
    private long flight_number;
    private String airline;
    private int number_of_seats;
    private Flight_type flight_type;
}
