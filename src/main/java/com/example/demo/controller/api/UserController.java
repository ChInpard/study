package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.service.UserService;
import com.example.demo.data.request.UserSearchRequest;

@RestController("apiUserController")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/{id}")
	public UserSearchRequest UserInfo(
						@PathVariable("id") Integer id) {
		
		return userService.getUser(id);
	}
	
	@PostMapping
	public UserSearchRequest createUser(
						@RequestBody UserSearchRequest request) {
		
		return userService.saveUser(request);
	}
	
}
