package com.tmn.ecommerce.security;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tmn.ecommerce.dao.UserJpaRepository;
import com.tmn.ecommerce.entity.Role;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${tmn.app.jwtsecret}")
	private String secretKey;

	@Value("${tmn.app.jwtsecret.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 1h


	@Autowired
	UserJpaRepository userJpaRepo;

	public String createToken(String username, Set<Role> roles) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + validityInMilliseconds))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new RuntimeException("Expired or invalid JWT token");
		}
	}

}
