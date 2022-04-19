package com.example.OODProject.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ScheduleStatus {
    @JsonProperty("on_time")
    ON_TIME,
    @JsonProperty("cancelled")
    CANCELLED,
    @JsonProperty("delayed")
    DELAYED,
    @JsonProperty("completed")
    COMPLETED
}
