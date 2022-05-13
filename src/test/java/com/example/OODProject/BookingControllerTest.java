package com.example.OODProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void view_bookings() throws Exception {
        this.mvc.perform(get("/api/booking/view")).andDo(print()).andExpect(status().isOk());
    }

//    @Test
//    public void CreateBookingTest() throws Exception {
//        Object booking = "{\"email\": \"chaitra@gmail.com\", \"schedule_id\": \"1\"}";
//        this.mvc.perform(post("/api/booking/add_booking").contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(booking.toString()))
//                .andExpect( status().isCreated() )
//                .andExpect(status().isAccepted());
//    }

    @Test
    public void deleteUserTest() throws Exception {
        int booking_id = 2;
        this.mvc.perform(delete("/api/booking/delete/"+booking_id))
                .andExpect( status().isOk() );
    }
}
