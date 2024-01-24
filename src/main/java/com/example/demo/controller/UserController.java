package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.controller.service.UserService;
import com.example.demo.data.dto.UserDTO;
import com.example.demo.data.entity.VipUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping
	public String userList(Model model) {
		List<VipUser> users = userService.getUserList();
		model.addAttribute("users", users);
		
		return "user_list";
	}
	
	@GetMapping("/{id}")
	public String userInfo(Model model,
						@PathVariable("id") Integer id) {
		Optional<VipUser> user = userService.getById(id);
		model.addAttribute("user", user);
		
		return "user_info";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new UserDTO());
		
		return "add_user";
	}
	
}
