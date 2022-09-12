package com.example.major1.Models;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String firstName;
    private  String surname;
    @Column(nullable = false , unique = true)

    private  String email;

    private String password;
    @ManyToMany(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID" , referencedColumnName = "ID")} , inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")})
    private List<Role> roles;

    public Users(Users users) {

        this.firstName = users.firstName;
        this.surname = users.surname;
        this.email = users.email;
        this.password = users.password;
        this.roles = users.roles;
    }
    public Users(){

    }
}
