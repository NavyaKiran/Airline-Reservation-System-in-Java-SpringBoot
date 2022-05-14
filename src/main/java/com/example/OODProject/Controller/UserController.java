package com.example.OODProject.Controller;

import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.Users;
import com.example.OODProject.Request.UserRequest;
import com.example.OODProject.Service.user_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan(basePackages = "com.example.OODProject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    user_services service;

    @PostMapping("/create_user")

    public ResponseEntity<?> add_user(@RequestBody UserRequest user)
    {
        //System.out.println("Here");
        return service.create_user(user);
    }

    @PutMapping("/update/{email}")

    public ResponseEntity update_user(
            @PathVariable String email,
            @RequestBody Users user)
    {
        return service.update(user, email);
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<?> delete_user(@PathVariable("id") String email) {
        return service.delete(email);
    }

    @GetMapping("/view")
    public ResponseEntity<?> view() {
        return service.view_all();
    }
}

