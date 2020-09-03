package com.tmn.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.Customer;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long>{

}
