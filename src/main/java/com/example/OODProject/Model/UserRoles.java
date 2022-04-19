package com.example.OODProject.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRoles {
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("user")
    USER
}
