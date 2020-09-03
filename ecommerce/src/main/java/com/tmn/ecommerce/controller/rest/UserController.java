package com.tmn.ecommerce.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmn.ecommerce.dao.RoleJpaRepository;
import com.tmn.ecommerce.dao.UserJpaRepository;
import com.tmn.ecommerce.dto.JwtResponse;
import com.tmn.ecommerce.dto.MessageResponse;
import com.tmn.ecommerce.dto.UserDto;
import com.tmn.ecommerce.security.JwtTokenProvider;
import com.tmn.ecommerce.service.UserService;
import com.tmn.ecommerce.service.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "http://localhost:4210")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserJpaRepository userRepository;

	@Autowired
	RoleJpaRepository roleRepository;


	@Autowired
	JwtTokenProvider jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetails;
	
	@PostMapping(path = "/register")
	public ResponseEntity<?> register(@RequestBody UserDto user) {
		if (userRepository.existsByname(user.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		this.userService.saveUser(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) {
		try {
		String tok = this.userService.login(user.getUsername(), user.getPassword());
		UserDetails userDetail = userDetails.loadUserByUsername(user.getUsername());
		List<String> roles =  userDetail.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(tok, user.getUsername(), user.getPassword(), roles));
		} catch (AuthenticationException e) {
			return ResponseEntity.ok(new JwtResponse(e.getMessage(), user.getUsername(), user.getPassword(), null));
		}
	}
}
