package com.example.OODProject.DataAccess;

import com.example.OODProject.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface users_dataaccess extends JpaRepository<Users, String> {


}
