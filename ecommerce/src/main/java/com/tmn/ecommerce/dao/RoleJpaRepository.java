package com.tmn.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.ERole;
import com.tmn.ecommerce.entity.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByname(ERole name);
}
