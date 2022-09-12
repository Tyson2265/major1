package com.example.major1.Controllers;

import com.example.major1.DAO.ProductDAO;
import com.example.major1.Models.Categories;
import com.example.major1.Models.Product;
import com.example.major1.Services.CategoriesService;
import com.example.major1.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller

public class AdminController {

   // public  static  String uploadDir =System.getProperty("user.dir")+ "src/main/resources/static.productImages";
    public String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static.productImages";
    @Autowired
    CategoriesService categoriesService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String AdminPage() {
        return "AdminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategoriesPage(Model model) {
        model.addAttribute("categories", categoriesService.categoriesList());
        return "Categories";
    }

    @GetMapping("/admin/categories/add")
    public String AddCategories(Model model) {
        model.addAttribute("categories", new Categories());
        return "CategoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String AddCategories(@ModelAttribute("Categories") Categories categories) {
        categoriesService.addCategoriesForm(categories);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete{id}")
    public String deleteCategories(@PathVariable int id) {
        categoriesService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update{id}")
    public String updateCategories(@PathVariable int id, Model model) {
        Optional<Categories> categories = categoriesService.getById(id);
        if (categories.isPresent()) {
            model.addAttribute("categories", categories.get());
            return "CategoriesAdd";
        }
        return "404";
    }


    //product section


    @GetMapping("/admin/products")

    public String getProductPage(Model model) {
        model.addAttribute("products", productService.productList());
        return "Products";
    }

    @GetMapping("/admin/products/add")
    public String AddProducts(Model model) {
        model.addAttribute("productDAO", new ProductDAO());
        model.addAttribute("categories" , categoriesService.categoriesList());
        return "ProductsAdd";

    }

    @PostMapping("/admin/products/add")
    public String AddProductsPost(@ModelAttribute("productDAO") ProductDAO productDAO,
                                  @RequestParam("productImages")MultipartFile file,
                                  @RequestParam ("imgName")String imgName) throws IOException
    {
        Product product = new Product();
        product.setId(productDAO.getId());
        product.setProduct_name(productDAO.getProduct_name());
        product.setCategories(product.getCategories());
       // product.setCategories(categoriesService.getById(productDAO.getCategoriesId()).orElseThrow(null));
        product.setPrice(productDAO.getPrice());
        product.setWeight(productDAO.getWeight());

        product.setDescription(productDAO.getDescription());

        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNamePath = Paths.get(uploadDir , imageUUID);
            Files.write(fileNamePath ,file.getBytes());
        }else {
            imageUUID= imgName;
        }
        product.setImageName(imageUUID);

        productService.addProductsForm(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete{id}")
    public String deleteProducts(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update{id}")
    public String updateProducts(@PathVariable int id, Model model) {
       Product product = productService.getById(id).get();
       ProductDAO productDAO = new ProductDAO();
       productDAO.setId(product.getId());
       productDAO.setProduct_name(product.getProduct_name());
       productDAO.setCategoriesId(product.getCategories().getId());
       productDAO.setPrice(product.getPrice());
       productDAO.setWeight(product.getWeight());
       productDAO.setDescription(product.getDescription());
       productDAO.setImageName(product.getImageName());

       model.addAttribute("categories" ,categoriesService.categoriesList());
       model.addAttribute("productDAO", productDAO);

        return "productsAdd";
    }
}