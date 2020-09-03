package com.tmn.ecommerce.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tmn.ecommerce.dao.RoleJpaRepository;
import com.tmn.ecommerce.dao.UserJpaRepository;
import com.tmn.ecommerce.dto.UserDto;
import com.tmn.ecommerce.entity.ERole;
import com.tmn.ecommerce.entity.Role;
import com.tmn.ecommerce.entity.User;
import com.tmn.ecommerce.security.JwtTokenProvider;
import com.tmn.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleJpaRepository roleRepository;

	@Autowired
	UserJpaRepository userRepository;
	
	@Autowired(required = true)
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public User saveUser(UserDto user) {
		User saveUser = new User(user.getUsername(), 
				user.getEmail(), encoder.encode(user.getPassword()));

		
		Set<String> strRoles = user.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByname(ERole.CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByname(ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByname(ERole.CLIENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		saveUser.setRoles(roles);
		saveUser.setCreatedDate(new Date());
		saveUser.setUpdatedDate(new Date());
		return this.userRepository.save(saveUser);
	}

	@Override
	public User findByUserName(String userName) {
		return this.userRepository.findByname(userName);
	}

	@Override
	public String login(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, this.userRepository.findByname(username).getRoles());
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new RuntimeException("username and password not match");
		}
	}
}
