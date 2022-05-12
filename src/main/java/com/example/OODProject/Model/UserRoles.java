package com.example.OODProject.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRoles {
    @JsonProperty("ADMIN")
    ADMIN,
    @JsonProperty("USER")
    USER
}
