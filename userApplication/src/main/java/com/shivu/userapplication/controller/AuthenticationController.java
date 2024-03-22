package com.shivu.userapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shivu.userapplication.exception.UserAlreadyExistsException;
import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.DisplayEmployees;
import com.shivu.userapplication.model.LoginDTO;
import com.shivu.userapplication.model.LoginResponse;
import com.shivu.userapplication.model.LoginResponseDTO;
import com.shivu.userapplication.model.RegistrationDTO;
import com.shivu.userapplication.repository.DepartmentRepository;
import com.shivu.userapplication.repository.RoleRepository;
import com.shivu.userapplication.repository.UserRepository;
import com.shivu.userapplication.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping("/loginb")
    public LoginResponse loginUser(@RequestBody LoginDTO body) throws Exception {
        try {
            LoginResponseDTO user = authenticationService.loginUser(body.getUsername(), body.getPassword());
            LoginResponse loginResponse = new LoginResponse(user.getUser().getUsername(),
                    user.getUser().getDepartment().getDepartmentName(),
                    user.getUser().getAuthorities(),
                    user.getJwt());
                    System.out.println(user.getUser().getPassword());
            return loginResponse;
        } catch (Exception e) {
            throw new Exception("Invalid Username and/or password .., Login Failed");
        }
    }

    @PostMapping("/registerb")
    public DisplayEmployees registerUser(@RequestBody RegistrationDTO body) throws Exception {
        if (userRepository.findByUsername(body.getUsername()).isPresent())
            throw new UserAlreadyExistsException("UserName Already Taken,");
        else if (!(userRepository.findByEmail(body.getEmail()) == null))
            throw new UserAlreadyExistsException("Email ALready Taken.");
        ApplicationUser user = authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail());
        DisplayEmployees displayUser = new DisplayEmployees(user.getUsername(),
                user.getDepartment().getDepartmentName(),
                user.getAuthorities());
        return displayUser ;
    }
}
