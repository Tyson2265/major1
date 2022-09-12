package com.example.major1.Controllers;

import com.example.major1.Global.GlobalData;
import com.example.major1.Models.Product;
import com.example.major1.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(productService.getById(id).get());
        return "redirect:/shop";

    }

    @GetMapping("/cart")
    public String fetCart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);

        return "cart";

    }

    @GetMapping("/cart/removeItem/{index}")
    public String removeItemRemove(@PathVariable int index) {

        GlobalData.cart.remove(index);
        return "redirect:/cart";

    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";

    }
}
