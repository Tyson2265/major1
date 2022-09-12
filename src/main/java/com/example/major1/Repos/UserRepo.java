package com.example.major1.Repos;

import com.example.major1.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo  extends JpaRepository<Users , Integer> {

    Optional<Users> findUsersByEmail (String email);
}
