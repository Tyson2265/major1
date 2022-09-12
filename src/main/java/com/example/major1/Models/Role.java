package com.example.major1.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false , unique = true)

    private  String role_name;
    @ManyToMany(mappedBy = "roles")
    private List<Users> usersList;

}
