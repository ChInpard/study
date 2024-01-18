package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HelloController {
	
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
	
	@GetMapping("/user/list")
	@ResponseBody
	public List<User> userList(Model model) {
		List<User> userList = this.userRepository.findAll();
		
		model.addAttribute("userList", userList);

		return userList;
	}
	
	@GetMapping("/user/id/{id}")
	@ResponseBody
	public Optional<User> userById(
						@PathVariable("id") Integer id) {
		
		Optional<User> user = this.userRepository.findById(id);
		System.out.println(id);
		
		return user;
	}
	
	@GetMapping("/user/name/{name}")
	@ResponseBody
	public Optional<User> userByName(
						@PathVariable("name") String name) {
		Optional<User> user = this.userRepository.findByName(name);
		
		return user;
	}
	
	@GetMapping("/user/email/{email}")
	@ResponseBody
	public Optional<User> userByEmail(
						@PathVariable("email") String email) {
		Optional<User> user = this.userRepository.findByEmail(email);
		
		return user;
	}
	
	@PostMapping("/userdb/create")
	@ResponseBody
	public User creadteUser(
						@RequestBody User newUser) {
		
		User createdUser = this.userRepository.save(newUser);
		
		return createdUser;
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public User updateUser(
						@PathVariable("id") Integer id,
						@RequestBody User modifiedUser) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		
		User updatedUser;
		
		if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(modifiedUser.getEmail());
            existingUser.setName(modifiedUser.getName());
            
            updatedUser = userRepository.save(existingUser);
            
            return updatedUser;
		}
		else {
			throw new RuntimeException("User not found with id: " + id);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public User deleteUser(
						@PathVariable("id") Integer id) {
		
		Optional<User> userToDelete = userRepository.findById(id);
		
		User deletedUser = userToDelete.get();
		this.userRepository.deleteById(id);
		
		return deletedUser;
	}
	
}