package com.example.OODProject.Service;

import com.example.OODProject.Model.Airport;
import com.example.OODProject.Request.AirportRequest;
import org.springframework.http.ResponseEntity;

public interface airport_services {
      public ResponseEntity<?> add_airport(AirportRequest request);
      public ResponseEntity<?> modify_airport(Airport airport, String airport_code);
      public ResponseEntity<?> delete_airport(String airport_code);
      public Airport view_specific_airport(String airport_code);
      public ResponseEntity<?> view_all();
}
