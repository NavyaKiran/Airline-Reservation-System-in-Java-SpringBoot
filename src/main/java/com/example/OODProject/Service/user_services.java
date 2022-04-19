package com.example.OODProject.Service;
import com.example.OODProject.Model.Users;
import org.springframework.http.ResponseEntity;

public interface user_services {
    public ResponseEntity<?> create_user(Users user);
    public ResponseEntity<?> findByEmail(String email);
    public ResponseEntity<?> update(Users user, String email);
    public ResponseEntity<?> delete(String email);
}
