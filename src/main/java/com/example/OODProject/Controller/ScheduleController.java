package com.example.OODProject.Controller;

import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.Flight;
import com.example.OODProject.Model.Schedule;
import com.example.OODProject.Model.Users;
import com.example.OODProject.Request.ScheduleRequest;
import com.example.OODProject.Service.schedule_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan(basePackages = "com.example.OODProject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    schedule_services service;

    @PostMapping("/create_schedule")
    public ResponseEntity<?> add_schedule(@RequestBody ScheduleRequest schedule)
    {
        System.out.println("Here");
        return service.add_schedule(schedule);
    }

    @DeleteMapping("/delete/{id}")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> delete_schedule(@PathVariable("id") Long schedule_id)
    {
        return service.delete_schedule(schedule_id);
    }

    @GetMapping("/view")
    public Iterable<Schedule> view() {
        return service.view_all();
    }

    @GetMapping("/viewByScheduleID/{id}")
    @ExceptionHandler(NotFoundException.class)
    public Schedule viewByScheduleID(@PathVariable("id") Long schedule_id)
    {
        return service.view_specific_schedule(schedule_id);
    }
}
