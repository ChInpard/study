package com.example.demo.data.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.UserDAO;
import com.example.demo.data.dto.UserDTO;
import com.example.demo.data.handler.UserDataHandler;

@Service
public class UserDataHandlerImpl implements UserDataHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserDTO userDTO;
	
	
	@Override
	public UserDTO getUserData(Integer userId) {
		
		userDTO = userDAO.getUser(userId);
		
		return userDTO;
	}

	@Override
	public UserDTO saveUserEntity(UserDTO userDTO) {
		
		userDTO = userDAO.saveUser(userDTO);
		
		return userDTO;
	}

}
