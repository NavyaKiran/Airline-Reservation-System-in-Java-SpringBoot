package com.example.OODProject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void CreateUserTest() throws Exception {
        Object user = "{\"email\": \"nav@gmail.com\", \"username\": \"navyakiran\",\"role\": \"user\", \"password\": \"12345\", \"phone_number\":\"1234567\"}";
        this.mvc.perform(post("/api/user/create_user").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(user.toString()))
                .andExpect( status().isCreated() )
                .andExpect(jsonPath("$.email").value("nav@gmail.com"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        String email = "nav@gmail.com";
        this.mvc.perform(delete("/api/user/delete/"+email))
                .andExpect( status().isAccepted() );
    }


}
