package com.shivu.userapplication.controller;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.LoginDTO;
import com.shivu.userapplication.model.LoginResponseDTO;
import com.shivu.userapplication.model.RegistrationDTO;
import com.shivu.userapplication.repository.UserRepository;
import com.shivu.userapplication.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

	
    @Autowired
    private AuthenticationService authenticationService;
    
    
    @Autowired
    private UserRepository userRepository;
   
   
    @PostMapping("/loginb")
   public LoginResponseDTO loginUser(@RequestBody LoginDTO body) throws Exception{
   	return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    
    @PostMapping("/registerb")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) throws Exception{
    	return authenticationService.registerUser(body.getUsername(), body.getPassword(),body.getEmail());
     }
}   
