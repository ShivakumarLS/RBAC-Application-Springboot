package com.shivu.userapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.shivu.userapplication.exception.UserNotFoundException;
import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    // @Autowired
    // private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }
    
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email);
        if (user!= null) 
        {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }
     
    public ApplicationUser getByResetPasswordToken(String token) throws Exception{
        return userRepository.findByResetPasswordToken(token).orElseThrow(()->new Exception("invalid token"));
    }
     
    public void updatePassword(ApplicationUser user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    
}