package com.tmn.ecommerce.service;

public interface SecurityService {
	
	String findLoggedInUserName();
	void autoLogin(String userName, String password);
}
