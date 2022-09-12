package com.example.major1.Services;

import com.example.major1.Models.Categories;
import com.example.major1.Models.Product;
import com.example.major1.Repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    public List<Product> productList (){

            return productRepo.findAll();
        }

    public void addProductsForm(Product product){
        productRepo.save(product);
    }
    public void deleteById(int id){
        productRepo.deleteById(id);
    }
    public Optional<Product> getById(int id) {
        return productRepo.findById( id);
    }
    public List<Product> getAllProductsByCategoriesId(int id){
        return  productRepo.findAllByCategoriesId(id);
    }
}




