package com.tmn.ecommerce.service;

import com.tmn.ecommerce.dto.UserDto;
import com.tmn.ecommerce.entity.User;

public interface UserService {

	User saveUser(UserDto user);
	User findByUserName(String userName);
	String login(String username,String password);
}
