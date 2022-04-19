package com.example.OODProject.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    private String airport_code;
    private String airport_name;
    private String airport_city;
    private String airport_state;
    private String airport_country;
}
