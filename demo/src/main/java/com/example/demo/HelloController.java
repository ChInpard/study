package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String list(Model model,
						@RequestParam(name="id", required=false) Integer id,
						@RequestParam(name="name", required=false) String name,
						@RequestParam(name="email", required=false) String email
						) {
		
		List<User> userList;
		Optional<User> findedUser;
		User user;
		
		model.addAttribute("userKey", null);
        
        userList = this.userRepository.findAll();
		model.addAttribute("userList", userList);
		
		if (id != null) {
	        findedUser = this.userRepository.findById(id);
            user = findedUser.get();
            model.addAttribute("userKey", user);
            model.addAttribute("userList", null);
	    }
		else if (name != null) {
			findedUser = this.userRepository.findByName(name);
            user = findedUser.get();
            model.addAttribute("userKey", user);
            model.addAttribute("userList", null);
		}
		else if (email != null) {
			findedUser = this.userRepository.findByEmail(email);
            user = findedUser.get();
            model.addAttribute("userKey", user);
            model.addAttribute("userList", null);
		}
		else {
	        model.addAttribute("userKey", null);
	        
	        userList = this.userRepository.findAll();
			model.addAttribute("userList", userList);
	    }
			
		return "user_list";
	}
	
//	@GetMapping("/list/{id}")
//	@ResponseBody
//	public String listById(Model model,
//						@PathVariable("id") Integer id) {
////		List<User> userList = this.userRepository.findAll();
////		model.addAttribute("userList", userList);
//		System.out.println(id);
//		return String.format("%d", id);
//	}
	
	@GetMapping("/list/{id}")
//	@ResponseBody
	public String listByIdFromDbTh(Model model,
						@PathVariable("id") Integer id) {

		Optional<User> findedUser = this.userRepository.findById(id);
		User user = findedUser.get();
		model.addAttribute("userKey", user);
		
		return "user_list";
	}
	
	@GetMapping("/listdb/{id}")
	@ResponseBody
	public User listByIdFromDb(Model model,
						@PathVariable("id") Integer id) {
		User user;
		Optional<User> findedUser = this.userRepository.findById(id);
		user = findedUser.get();
//		model.addAttribute("user", user);
		
		return user;
	}
	
//	@GetMapping("/list")
//	@ResponseBody
//	public User listById(Model model,
//						@PathVariable("id") Integer id) {
//		User user;
//		Optional<User> findedUser = this.userRepository.findById(id);
//		user = findedUser.get();
////		model.addAttribute("user", user);
//		
//		return user;
//	}
	
	
	@PostMapping("/userdb/create")
	@ResponseBody
	public String postUser(Model model, @RequestBody User newUser) {
		
		User createdUser = this.userRepository.save(newUser);
		
		return "createdUser";
	}
	
}