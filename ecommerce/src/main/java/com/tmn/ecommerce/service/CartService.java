package com.tmn.ecommerce.service;

import java.util.List;

import com.tmn.ecommerce.dto.CartDto;
import com.tmn.ecommerce.entity.User;

public interface CartService {

	CartDto saveCart(String itemId,User loggedUser);
	List<CartDto> viewCart(String email);
	CartDto searchCartByIdEmailQuantity(Long id, String email, int quantity);
	void deleteCartById(long cartId,String email);
	void deleteCartByEmail(String email);
}
