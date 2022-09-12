package com.example.major1.Models;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String product_name;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne//(fetch = FetchType.LAZY)

    @JoinColumn(name = "id" ,referencedColumnName = "id"  ,insertable=false, updatable=false , nullable = false)
    private Categories categories;

    private double price;
    private double weight;
    private String description;
    private String imageName;

}
