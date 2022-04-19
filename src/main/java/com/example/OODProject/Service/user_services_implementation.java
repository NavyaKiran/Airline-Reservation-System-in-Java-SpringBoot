package com.example.OODProject.Service;

import com.example.OODProject.Exception.NotFoundException;
import com.example.OODProject.Model.UserRoles;
import com.example.OODProject.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.OODProject.DataAccess.users_dataaccess;
import com.example.OODProject.Exception.AvailableRecordException;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class user_services_implementation implements user_services {

    @Autowired
    users_dataaccess userObj;

    @Override
    public ResponseEntity<?> create_user(Users user) {
        Optional<Users> findByUserID = userObj.findById(user.getEmail());
        try {
            if (!findByUserID.isPresent()) {
                user.setRole(UserRoles.USER);
                userObj.save(user);
                return new ResponseEntity<Users>(user, HttpStatus.OK);
            } else
                throw new AvailableRecordException("Record of the user with ID " +user.getEmail()+ " is present, cannot add a new record");
        } catch (AvailableRecordException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> findByEmail(String email) {
        Optional<Users> findUsingID = userObj.findById(email);
        try {
            if (findUsingID.isPresent()) {
                Users userfound = findUsingID.get();
                return new ResponseEntity<Users>(userfound, HttpStatus.OK);
            } else {
                throw new NotFoundException("User with email " +email+ " not found");
            }
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?>  update(Users updateuser, String email) {
        try {
            Users user = userObj.findById(email).get();
            if (user == null)
                throw new NotFoundException("The user with email " + email + " has not been found");

            if (updateuser.getUsername() != null) {
                user.setUsername(updateuser.getUsername());
            }

            if (!Objects.isNull(updateuser.getPhone_number()) && updateuser.getPhone_number() != 0) {
                user.setPhone_number(updateuser.getPhone_number());
            }
//        if(updateuser.getRole() != user.getRole())
//        {
//            user.setRole(updateuser.getRole());
//        }
            userObj.save(user);
            return new ResponseEntity<Users>(user, HttpStatus.OK);
        }
        catch(NotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> delete(String email) {
        Optional<Users> findByEmail = userObj.findById(email);
        if (findByEmail.isPresent()) {
            userObj.deleteById(email);
            return new ResponseEntity<>("The user with email " + email + "has been deleted", HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<>("User with email " + email + "could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }

