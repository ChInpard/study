package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HelloController {
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hellow World!";
	}
	
	private final UserRepository userRepository;
	
	@GetMapping("/list")
//	@ResponseBody
	public String list(Model model) {
		List<User> userList = this.userRepository.findAll();
		model.addAttribute("userList", userList);
		return "user_list";
	}
}