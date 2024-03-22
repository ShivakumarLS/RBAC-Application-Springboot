package com.shivu.userapplication.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.Department;
import com.shivu.userapplication.model.LoginResponseDTO;
import com.shivu.userapplication.model.Role;
import com.shivu.userapplication.repository.DepartmentRepository;
import com.shivu.userapplication.repository.RoleRepository;
import com.shivu.userapplication.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	DepartmentRepository departmentRepository;

	public ApplicationUser registerUser(String username, String password, String email) throws  Exception {

		String encodedPassword = passwordEncoder.encode(password);
		Role userRole = roleRepository.findByAuthority("USER").get();
		Role generalRole = roleRepository.findByAuthority("GENERAL").get();
		Department userDepartment = new Department(2,"USER");
		departmentRepository.save(userDepartment);
		Set<Role> authorities = new HashSet<>();
		authorities.add(userRole);
		authorities.add((generalRole));
		String resetPasswordToken = null;
		return userRepository
				.save(new ApplicationUser(0, username, encodedPassword, authorities,userDepartment,email, resetPasswordToken));
	}

	public LoginResponseDTO loginUser(String username, String password) {

		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			String token = tokenService.generateJwt(auth);
			System.out.println(userRepository.findByUsername(username).get().getDepartment().getDepartmentName());
			return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

		} catch (AuthenticationException e) {
			throw  new  ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials!");
		}
	}
}