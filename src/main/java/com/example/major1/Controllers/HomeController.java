package com.example.major1.Controllers;

import com.example.major1.Global.GlobalData;
import com.example.major1.Models.Product;
import com.example.major1.Services.CategoriesService;
import com.example.major1.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class HomeController {

    @Autowired
    CategoriesService categoriesService;
    @Autowired
    ProductService productService;

    @GetMapping({"/" ,"/home"})
    public String home(Model model){
        model.addAttribute("cartCount" , GlobalData.cart.size());
        return "index";

    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute(
                "categories" ,categoriesService.categoriesList()

        );
        model.addAttribute(
                "products" ,productService.productList()

        );
        model.addAttribute("cartCount" , GlobalData.cart.size());
        return "shop";

    }

    @GetMapping("/shop/categories/{id}")
    public String shopByCategories(@PathVariable int id , Model model){
        model.addAttribute(
                "categories" ,categoriesService.categoriesList()

        );
        model.addAttribute("cartCount" , GlobalData.cart.size());
        model.addAttribute(
                "products" ,productService.getAllProductsByCategoriesId(id)

        );
        return "shop";

    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable int id , Model model){

        model.addAttribute(
                "product" ,productService.getById(id).get()

        );
        model.addAttribute("cartCount" , GlobalData.cart.size());
        return "viewProduct";

    }






}
