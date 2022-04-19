package com.example.OODProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    private long flight_number;
    private String airline;
    private int number_of_seats;

    @Enumerated(EnumType.STRING)
    private Flight_type flight_type;
}
