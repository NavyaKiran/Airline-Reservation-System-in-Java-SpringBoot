package com.example.OODProject.Request;

import com.example.OODProject.Model.Schedule;
import com.example.OODProject.Model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private long booking_id;
    private String email;
    private long schedule_id;
}
