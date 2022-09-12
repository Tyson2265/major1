package com.example.major1.Repos;

import com.example.major1.Models.Categories;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo  extends JpaRepository<Categories,Integer> {
}
