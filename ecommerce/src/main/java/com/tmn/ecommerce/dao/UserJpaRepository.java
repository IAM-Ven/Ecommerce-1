package com.tmn.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{

	User findByname(String name);
	Boolean existsByname(String name);
	Boolean existsByEmail(String email);
	User findByEmailAndPassword(String email, String password);

}
