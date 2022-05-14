package com.example.OODProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles role;
//    @NotBlank(message = "Username required, please enter Username")
    private String username;
//    @NotBlank(message = "Password required, please enter password")
    private String password;
    private long phone_number;
}

