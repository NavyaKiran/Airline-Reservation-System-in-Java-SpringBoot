package com.example.OODProject.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class AirportRequest {
    private String airport_code;
    private String airport_name;
    private String airport_city;
    private String airport_state;
    private String airport_country;
}
