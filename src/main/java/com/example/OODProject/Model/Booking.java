package com.example.OODProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int booking_id;

    @ManyToOne
    @JoinColumn(name="email", referencedColumnName = "email")
    private Users email;

    @ManyToOne
    @JoinColumn(name="schedule_id", referencedColumnName = "schedule_id")
    private Schedule schedule_id;
}
