package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	@GetMapping
	public String index() {
		
		return "index";
	}
	
	private final UserRepository userRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<User> userList;
		
		model.addAttribute("userKey", null);
        
        userList = this.userRepository.findAll();
		model.addAttribute("userList", userList);
			
		return "user_list";
	}

}