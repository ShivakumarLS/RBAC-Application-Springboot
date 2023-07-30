package com.shivu.userapplication.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.Role;
import com.shivu.userapplication.repository.UserRepository;
import com.shivu.userapplication.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	
	
	@GetMapping("/")
	public String helloAdmineController() {
		return "Admin level access";
	}

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/getusers")
	public List<ApplicationUser> getusers() {
		List<ApplicationUser> users = new ArrayList<>();
		users = userRepo.findAll();
		return users;
	}

	@GetMapping("/getuser/{uname}")
	public ApplicationUser getuserById(@PathVariable("uname") String uname) throws Exception {
		String uName = uname;
		List<ApplicationUser> users = new ArrayList<>();

		Optional<ApplicationUser> user = userRepo.findByUsername(uName);
		
		return user.orElseThrow(()-> new Exception("user not found"));
	}
	
	@DeleteMapping("/delete/{uname}")
	public Boolean deleteUserById(@PathVariable("uname") String uname) throws Exception {

		ApplicationUser user = userRepo.findByUsername(uname).orElse(null);
		if(user !=null)
		{
			userRepo.deleteByUsername(uname);

			return true;
		} else
			throw new Exception("no user with given username" + uname);
	}

	
	@DeleteMapping("/deleteall")
	public Boolean deleteUsers() throws Exception {

		List<ApplicationUser> users = userRepo.findAll();
		if (!users.isEmpty()) {
			userRepo.deleteAll();
			return true;
		} else
			throw new Exception(" Record is Empty");

	}

	@PutMapping("/update/{uname}")
	public ApplicationUser update(@PathVariable("uname")String userName,@RequestBody ApplicationUser user) throws Exception {

		Optional<ApplicationUser> optUser = userRepo.findByUsername(userName);
		if (!optUser.isPresent()) {
			throw new Exception("User with username " + userName + " does not exist");
		}
		ApplicationUser obj = optUser.get();
		
		if (user.getUsername() != null) {
			obj.setUsername(user.getUsername());
		}
		userRepo.save(obj);
		return obj;
	}
}