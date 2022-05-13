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
public class AirportControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void view_airport() throws Exception {
        this.mvc.perform(get("/api/airport/view")).andDo(print()).andExpect(status().isOk());
    }

        @Test
        public void CreateAirportTest() throws Exception {
            Object airport = "{\"airport_code\": \"SJC\", \"airport_name\": \"San Jose Intl\",\"airport_city\": \"San Jose\", \"airport_state\": \"California\", \"airport_country\":\"United States of America\"}";
            this.mvc.perform(post("/api/airport/add_airport").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(airport.toString()))
                    .andExpect( status().isCreated() )
                    .andExpect(jsonPath("$.airport_code").value("SJC"));
        }

        @Test
        public void deleteUserTest() throws Exception {
            String airport_code = "SJC";
            this.mvc.perform(delete("/api/airport/delete/"+airport_code))
                    .andExpect( status().isAccepted() );
        }
    }


