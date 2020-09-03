package com.tmn.ecommerce.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tmn.ecommerce.dao.UserJpaRepository;
import com.tmn.ecommerce.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = userJpaRepository.findByname(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found!");

		// Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		for (Role role : user.getRoles()) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(),
//				user.getPassword(), grantedAuthorities);
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				authorities);
		// return UserDetailsImpl.build(user);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
