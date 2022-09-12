package com.example.major1.Repos;

import com.example.major1.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo  extends JpaRepository<Role , Integer> {

}
