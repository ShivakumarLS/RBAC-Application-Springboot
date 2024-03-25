package com.shivu.userapplication.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.Department;
import com.shivu.userapplication.model.DisplayEmployees;
import com.shivu.userapplication.repository.DepartmentRepository;
import com.shivu.userapplication.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/")
	public String helloAdminController() {
		return "Admin level access";
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired DepartmentRepository departmentRepository;

	@GetMapping("/getusers")
	public List<DisplayEmployees> getusers() {
		List<ApplicationUser> users = new ArrayList<>();
		users = userRepository.findAll();
		List<DisplayEmployees> showUsers = users.stream()
				.map(displayUser -> new DisplayEmployees(displayUser.getUsername(),
						displayUser.getDepartment().getDepartmentName(), displayUser.getAuthorities()))
				.collect(Collectors.toList());
		users = userRepository.findAll();
		return showUsers;
	}

	public List<ApplicationUser> getUsers() {
		List<ApplicationUser> users = new ArrayList<>();
		users = userRepository.findAll();
		return users;
	}

	@GetMapping("/getusers/{dept}")
	public List<ApplicationUser> getUsersByDepartment(@PathVariable("dept") String department) throws Exception
	{
		ArrayList<ApplicationUser> users =  userRepository.findAllByDepartmentName(department);
		return users;
	}

	@GetMapping("/getdepartments")
	public Set<String> getDepartments() {
		Set<String> departments = departmentRepository.findAll()
		.stream().map(u->u.getDepartmentName()).collect(Collectors.toUnmodifiableSet());
		return departments;
	}
	@GetMapping("/getuser/{uname}")
	public ApplicationUser getuserById(@PathVariable("uname") String uname) throws Exception {
		String uName = uname;
		Optional<ApplicationUser> user = userRepository.findByUsername(uName);
		return user.orElseThrow(() -> new Exception("user not found"));
	}

	@DeleteMapping("/delete/{uname}")
	public Boolean deleteUserById(@PathVariable("uname") String uname) throws Exception {

		ApplicationUser user = userRepository.findByUsername(uname)
				.orElseThrow(() -> new Exception("no user with given username" + uname));
		if (user != null) {
			userRepository.deleteByUsername(uname);
			return true;
		} else
			throw new Exception("no user with given username" + uname);
	}

	@DeleteMapping("/deleteall")
	public Boolean deleteUsers() throws Exception {
		List<ApplicationUser> users = userRepository.findAll();
		if (!users.isEmpty()) 
		{
			userRepository.deleteAll();
			return true;
		} 
		else
			throw new Exception("Record is Empty");
	}

	@PutMapping("/update/{uname}")
	public ApplicationUser update(@PathVariable("uname") String userName, @RequestBody ApplicationUser user)
			throws Exception {
		Optional<ApplicationUser> optionalUser = userRepository.findByUsername(userName);
		if (!optionalUser.isPresent()) {
			throw new Exception("User with username " + userName + " does not exist");
		}
		ApplicationUser User = optionalUser.get();
		if (user.getUsername() != null) {
			User.setUsername(user.getUsername());

			userRepository.save(User);
			return User;
		}
		return optionalUser.get();
	}

}