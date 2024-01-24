package com.example.demo.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.UserDAO;
import com.example.demo.data.dto.UserDTO;
import com.example.demo.data.entity.VipUser;
import com.example.demo.data.repository.UserRepository;

@Service
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDTO userDTO;
	
	@Autowired
	private VipUser user;
	
	
	@Override
	public UserDTO getUser(Integer userId) {
		
		user = userRepository.getReferenceById(userId);
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAge(user.getAge());
		
		return userDTO;
	}
	
	@Override
	public UserDTO saveUser(UserDTO userDTO) {

		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setAge(userDTO.getAge());
		user.setIsAdult(userDTO.getAge() > 19 ? true : false);
		
		user = userRepository.save(user);
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAge(user.getAge());
		
		return userDTO;
	}

	
	@Override
	public List<VipUser> getList() {
		
		return this.userRepository.findAll();
	}

	@Override
	public Optional<VipUser> getById(Integer id) {
		
		return this.userRepository.findById(id);
	}

}
