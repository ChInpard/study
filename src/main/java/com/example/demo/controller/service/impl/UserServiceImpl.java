package com.example.demo.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.service.UserService;
import com.example.demo.data.dao.UserDAO;
//import com.example.demo.data.dao.UserDAO;
import com.example.demo.data.dto.UserDTO;
import com.example.demo.data.entity.VipUser;
import com.example.demo.data.handler.UserDataHandler;
import com.example.demo.data.request.UserSearchRequest;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDataHandler userDataHandler;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserDTO userDTO;
	
	@Autowired
	private UserSearchRequest request;
	
	
	@Override
	public UserSearchRequest getUser(Integer userId) {
		
		userDTO = userDataHandler.getUserData(userId);
		
		request.setId(userDTO.getId());
		request.setName(userDTO.getName());
		request.setAge(userDTO.getAge());
		
		return request;
	}
	
	@Override
	public UserSearchRequest saveUser(UserSearchRequest request) {
		
		userDTO.setId(request.getId());
		userDTO.setName(request.getName());
		userDTO.setAge(request.getAge());
		
		userDTO = userDataHandler.saveUserEntity(userDTO);
		
		request.setId(userDTO.getId());
		request.setName(userDTO.getName());
		request.setAge(userDTO.getAge());
		
		return request;
	}

	@Override
	public List<VipUser> getUserList() {
		
		return userDAO.getList();
	}

	@Override
	public Optional<VipUser> getById(Integer id) {
		
		return userDAO.getById(id);
	}

}
