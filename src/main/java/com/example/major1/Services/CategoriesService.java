package com.example.major1.Services;

import com.example.major1.Models.Categories;
import com.example.major1.Repos.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;
   public List<Categories> categoriesList (){
       return categoriesRepo.findAll();
   }
   public void addCategoriesForm(Categories categories){
       categoriesRepo.save(categories);
   }

   public void deleteById(int id){
       categoriesRepo.deleteById(id);
   }
    public Optional<Categories> getById(int id){
       return categoriesRepo.findById(id);
    }
}
