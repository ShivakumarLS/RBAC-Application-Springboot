package com.shivu.userapplication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.Department;
import com.shivu.userapplication.model.Role;
import com.shivu.userapplication.repository.DepartmentRepository;
import com.shivu.userapplication.repository.RoleRepository;
import com.shivu.userapplication.repository.UserRepository;
import com.shivu.userapplication.service.RBACService;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserRepository userRepository,RoleRepository roleRepository,DepartmentRepository departmentRepository,RBACService rbacService,PasswordEncoder passwordEncode)
	{
	return args->{
		rbacService.initialize();
		Role adminRole = roleRepository.findByAuthority("ADMIN").orElseThrow( ()->  new Exception("Initialize data failed"));
		Department adminDepartment = departmentRepository.findByDepartmentName("ADMIN").orElseThrow(()->  new RuntimeException("No department"));
		String adminEmail="admin@email.com";
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		String resetPasswordToken=null;
		ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles,adminDepartment,adminEmail,resetPasswordToken);
		userRepository.save(admin);
	};
	}
}
