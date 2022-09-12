package com.example.major1.Config;

import com.example.major1.Models.Role;
import com.example.major1.Models.Users;
import com.example.major1.Repos.RoleRepo;
import com.example.major1.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandlder implements AuthenticationSuccessHandler {


    @Autowired
    RoleRepo roleRepo;
@Autowired
    UserRepo userRepo;

private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        OAuth2AuthenticationToken token =(OAuth2AuthenticationToken) authentication;
        String email =token.getPrincipal().getAttributes().get("email").toString();

        if(userRepo.findUsersByEmail(email).isPresent()){

        }
        else {
            Users users = new Users();
            users.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            users.setSurname(token.getPrincipal().getAttributes().get("family_name").toString());
            users.setEmail(email);
            List<Role> roles = new ArrayList<>();

            roles.add(roleRepo.findById(2).get());
            users.setRoles(roles);
            userRepo.save(users);


        }


        redirectStrategy.sendRedirect(httpServletRequest , httpServletResponse , "/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {




    }
}
