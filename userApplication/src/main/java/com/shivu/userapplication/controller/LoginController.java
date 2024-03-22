package com.shivu.userapplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shivu.userapplication.model.LoginDTO;
import com.shivu.userapplication.model.LoginResponseDTO;
import com.shivu.userapplication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("LoginForm", new LoginDTO());
		return "login";

	}

	@PostMapping("/login")
	public String LoginUser(@ModelAttribute LoginDTO user, Model model, HttpServletResponse response) throws Exception {
		model.addAttribute("LoginForm", new LoginDTO());
		LoginResponseDTO resp = authenticationService.loginUser(user.getUsername(), user.getPassword());
		if (resp.getJwt().equals(""))
			return "redirect:/auth/login?error";
		
		model.addAttribute("UserName", user.getUsername());
		
		System.out.println(resp.getJwt());
		return "welcome";
	}

}
