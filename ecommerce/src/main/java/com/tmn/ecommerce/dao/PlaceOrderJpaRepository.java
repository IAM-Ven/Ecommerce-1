package com.tmn.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.PlaceOrder;

@Repository
public interface PlaceOrderJpaRepository extends JpaRepository<PlaceOrder, Long>{

}
