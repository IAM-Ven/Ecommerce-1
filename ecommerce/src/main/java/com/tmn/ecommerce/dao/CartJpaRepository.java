package com.tmn.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.Cart;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Long>{
	
	@Query(value = "Select * from Cart cart "+
			"where cart.email = :email",nativeQuery=true)
	List<Cart> getCartByEmail(@Param("email")String email);
	
	@Query(value = "Select * from Cart cart "+
			"where cart.cart_id = :id and cart.email = :email",nativeQuery=true)
	Cart getCartByIdAndEmail(@Param("id")Long id,@Param("email")String email);

	@Query(value = "Select * from Cart cart "+
			"where cart.email = :email",nativeQuery=true)
	Cart getCartByIdAndEmail(@Param("email")String email);
}
