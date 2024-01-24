package com.example.demo.data.handler;

import com.example.demo.data.dto.UserDTO;

public interface UserDataHandler {

	UserDTO getUserData(Integer userId);
	
	UserDTO saveUserEntity(UserDTO userDTO);
	
}
