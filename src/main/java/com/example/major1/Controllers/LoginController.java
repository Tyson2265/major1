package com.example.major1.Controllers;

import com.example.major1.Global.GlobalData;
import com.example.major1.Models.Role;
import com.example.major1.Models.Users;
import com.example.major1.Repos.RoleRepo;
import com.example.major1.Repos.UserRepo;
import com.example.major1.Services.CategoriesService;
import com.example.major1.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";

    }

    @GetMapping({"/register"})
    public String getRegister(){
        return "register";

    }

    @PostMapping({"/register"})
    public String postRegister(@ModelAttribute ("user") Users users , HttpServletRequest request) throws ServletException {
      String password = users.getPassword();
      users.setPassword(bCryptPasswordEncoder.encode(password));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findById(2).get());
        users.setRoles(roles);
        userRepo.save(users);
        request.login(users.getEmail(),password);
        return "redirect:/";

    }



}
