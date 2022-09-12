package com.example.major1.DAO;

import com.example.major1.Models.Categories;
import lombok.Data;

import javax.persistence.*;
@Data
public class ProductDAO {


    private int id;

    private String product_name;


    private int CategoriesId;

    private double price;
    private double weight;
    private String description;
    private String imageName;


}
