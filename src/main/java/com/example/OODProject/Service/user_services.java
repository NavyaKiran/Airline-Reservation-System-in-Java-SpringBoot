package com.example.OODProject.Service;
import com.example.OODProject.Model.Users;
import com.example.OODProject.Request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface user_services {
    public ResponseEntity<?> create_user(UserRequest request);
    public ResponseEntity<?> findByEmail(String email);
    public ResponseEntity<?> update(Users user, String email);
    public ResponseEntity<?> delete(String email);
    public ResponseEntity<?> view_all();
}
