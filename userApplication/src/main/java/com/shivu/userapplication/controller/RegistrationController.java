package com.shivu.userapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.RegistrationDTO;
import com.shivu.userapplication.repository.UserRepository;
import com.shivu.userapplication.service.AuthenticationService;
import com.shivu.userapplication.service.UserService;


@Controller
@RequestMapping("/auth/register")
public class RegistrationController {
	
    
    @Autowired
    AuthenticationService authenticationService;
    
    @Autowired
    UserRepository userRepository;
    
	@GetMapping
    public String showRegistrationForm(Model model)
    {
		model.addAttribute("RegistrationDTOform",new RegistrationDTO());
    	return "registration";
    }
    
    @PostMapping
    public String registerUser(@ModelAttribute RegistrationDTO user, Model model) throws Exception{
      model.addAttribute("RegistrationDTOform",new RegistrationDTO());
      if (userRepository.findByUsername(user.getUsername()).isPresent())
    	  return "redirect:/auth/register?userpresent";
      authenticationService.registerUser(user.getUsername(),user.getPassword(),user.getEmail());
     
    return "redirect:/auth/register?success";
    }

}
