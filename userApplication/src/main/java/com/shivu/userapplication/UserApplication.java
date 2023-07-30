package com.shivu.userapplication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.Role;
import com.shivu.userapplication.repository.RoleRepository;
import com.shivu.userapplication.repository.UserRepository;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncode)
	
	{
	return args->{
		if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
		Role adminRole = roleRepository.save(new Role("ADMIN"));
		roleRepository.save(new Role("USER"));
		String adminEmail="admin@email.com";
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		String resetPasswordToken=null;
		ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles,adminEmail,resetPasswordToken);
		userRepository.save(admin);
	};
		
	}
}
