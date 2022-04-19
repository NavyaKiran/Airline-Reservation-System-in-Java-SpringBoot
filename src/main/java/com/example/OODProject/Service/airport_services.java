package com.example.OODProject.Service;

import com.example.OODProject.Model.Airport;
import org.springframework.http.ResponseEntity;

public interface airport_services {
      public ResponseEntity<?> add_airport(Airport airport);
      public ResponseEntity<?> modify_airport(Airport airport, String airport_code);
      public ResponseEntity<?> delete_airport(String airport_code);
      public Airport view_specific_airport(String airport_code);
      public Iterable<Airport> view_all();
}
