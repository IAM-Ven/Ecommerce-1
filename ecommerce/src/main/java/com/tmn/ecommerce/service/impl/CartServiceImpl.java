package com.tmn.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmn.ecommerce.dao.CartJpaRepository;
import com.tmn.ecommerce.dao.ItemJpaRepository;
import com.tmn.ecommerce.dto.CartDto;
import com.tmn.ecommerce.entity.Cart;
import com.tmn.ecommerce.entity.Item;
import com.tmn.ecommerce.entity.User;
import com.tmn.ecommerce.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	CartJpaRepository cartJpaRepo;
	
	@Autowired
	ItemJpaRepository itemJpaRepo;
	
	@Override
	public CartDto saveCart(String itemId,User loggedUser) {
		Item item = itemJpaRepo.getOne(Long.parseLong(itemId));
		Cart cart = new Cart();
		//cart.setEmail(loggedUser.getEmail());
		cart.setQuantity(1);
		cart.setPrice(item.getPrice());
		Date date = new Date();
		//cart.setDateAdded(date);
		this.cartJpaRepo.save(cart);
		return new CartDto();
	}
	
	@Override
	public List<CartDto> viewCart(String email) {
		List<Cart> cartList = this.cartJpaRepo.getCartByEmail(email);
		List<CartDto> dtoList = new ArrayList<CartDto>();
		for (Cart cart : cartList) {
			CartDto cartDto = new CartDto();
			dtoList.add(cartDto);
		}
		return dtoList;
	}
	
	
	@Override
	public CartDto searchCartByIdEmailQuantity(Long id, String email,int quantity) {
		Cart cart = this.cartJpaRepo.getCartByIdAndEmail(id, email);
		cart.setQuantity(quantity);
		this.cartJpaRepo.save(cart);
		CartDto cartDto = new CartDto();
		return cartDto;
	}
	
	@Override
	public void deleteCartById(long cartId,String email) {
		Cart cart = this.cartJpaRepo.getCartByIdAndEmail(cartId, email);
		this.cartJpaRepo.delete(cart);
	}
	
	
	public Long randomOrderId() {
	      int min = 0;
	      int max = 100000;
//	      //Generate random double value from 0 to 100 
//	      System.out.println("Random value in double from "+min+" to "+max+ ":");
//	      double random_double = Math.random() * (max - min + 1) + min; 
//	      System.out.println(random_double);
	        
	      //Generate random int value from 50 to 100 
	      Long random_int = (long) (Math.random() * (max - min + 1) + min);
	      return random_int;
	    }

	@Override
	public void deleteCartByEmail(String email) {
		Cart cart = this.cartJpaRepo.getCartByIdAndEmail(email);
		this.cartJpaRepo.delete(cart);
	}

}
