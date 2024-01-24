package com.example.demo.data.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.dto.UserDTO;
import com.example.demo.data.entity.VipUser;


public interface UserDAO {

	UserDTO getUser(Integer userId);
	
	UserDTO saveUser(UserDTO userDTO);

	List<VipUser> getList();

	Optional<VipUser> getById(Integer id);
	
}
