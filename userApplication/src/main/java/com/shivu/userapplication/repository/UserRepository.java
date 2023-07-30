package com.shivu.userapplication.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivu.userapplication.model.ApplicationUser;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
	Optional<ApplicationUser> findByUsername(String username);
	
	@Transactional
	Optional<ApplicationUser> deleteByUsername(String username);
	
	
	ApplicationUser findByEmail(String email);
	
	
	Optional<ApplicationUser> findByResetPasswordToken(String token);
}
