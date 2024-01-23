package com.example.demo.controller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.service.UserService;
//import com.example.demo.data.dao.UserDAO;
import com.example.demo.data.dto.UserDTO;
import com.example.demo.data.entity.VipUser;
import com.example.demo.data.handler.UserDataHandler;
import com.example.demo.data.request.UserSearchRequest;


@Service
public class UserServiceImpl implements UserService {
	
//	private UserDAO userDAO;
	private UserDataHandler userDataHandler;
	
	@Autowired
	public UserServiceImpl(UserDataHandler userDataHandler) {
//		this.userDAO = userDAO;
		this.userDataHandler = userDataHandler;
	}
	
	@Override
	public UserSearchRequest getUser(Integer userId) {
		
		UserSearchRequest request = new UserSearchRequest();
		
		VipUser user = userDataHandler.getUserEntity(userId);
		
		request.setId(user.getId());
		request.setName(user.getName());
		request.setAge(user.getAge());
		
		return request;
	}
	
	@Override
	public VipUser saveUser(UserDTO userDTO) {
		
		return userDataHandler.saveUserEntity(userDTO);
	}
}
