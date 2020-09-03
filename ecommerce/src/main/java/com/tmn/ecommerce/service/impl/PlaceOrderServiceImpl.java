package com.tmn.ecommerce.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmn.ecommerce.dao.CartJpaRepository;
import com.tmn.ecommerce.dao.ItemJpaRepository;
import com.tmn.ecommerce.dao.PlaceOrderJpaRepository;
import com.tmn.ecommerce.entity.Cart;
import com.tmn.ecommerce.entity.PlaceOrder;
import com.tmn.ecommerce.entity.User;
import com.tmn.ecommerce.service.PlaceOrderService;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService{

	@Autowired
	CartJpaRepository cartJpaRepo;
	
	@Autowired
	PlaceOrderJpaRepository placeOrderJpaRepo;
	
	@Autowired
	ItemJpaRepository itemJpaRepo;
	
	@Override
	public void saveOrder(User loggedUser) {
		double total = 0;
		List<Cart> cartList = this.cartJpaRepo.getCartByEmail(loggedUser.getEmail());
		PlaceOrder placeOrder = new PlaceOrder();
		placeOrder.setEmail(loggedUser.getEmail());
		for (Cart cart : cartList) {
			total =+ (cart.getPrice() * cart.getQuantity());
			placeOrder.setCost(total);
		}
		placeOrder.setOrderStatus("Pending");
		Date date = new Date();
		placeOrder.setOrderDate(date);	
		placeOrderJpaRepo.save(placeOrder);
		
	}
	
//	private void reduceQuantity(int quantity) {
//		this.itemJpaRepo.findById(id)
//	}

}
