package com.example.OODProject.Service;

import com.example.OODProject.Model.Flight;
import com.example.OODProject.Model.Schedule;
import com.example.OODProject.Request.ScheduleRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface schedule_services {
    public ResponseEntity<?> add_schedule(ScheduleRequest schedule);
//    public ResponseEntity<?> modify_schedule(Schedule schedule, Long schedule_id);
    public ResponseEntity<?> delete_schedule(Long schedule_id);
    public Schedule view_specific_schedule(Long schedule_id);
    public ResponseEntity<?> view_all();
}
