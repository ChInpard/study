package com.example.demo.controller.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.entity.VipUser;
import com.example.demo.data.request.UserSearchRequest;

public interface UserService {
	
	UserSearchRequest getUser(Integer userId);
	
	UserSearchRequest saveUser(UserSearchRequest request);

	List<VipUser> getUserList();

	Optional<VipUser> getById(Integer id);

}
