package com.example.OODProject.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Flight_type {
    @JsonProperty("boeing")
    BOEING,
    @JsonProperty("airbus")
    AIRBUS
}
