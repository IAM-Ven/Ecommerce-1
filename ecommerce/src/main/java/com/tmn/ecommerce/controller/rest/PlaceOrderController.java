package com.tmn.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmn.ecommerce.dto.MessageResponse;
import com.tmn.ecommerce.entity.User;
import com.tmn.ecommerce.security.JwtTokenProvider;
import com.tmn.ecommerce.service.CartService;
import com.tmn.ecommerce.service.PlaceOrderService;
import com.tmn.ecommerce.service.UserService;

@RestController
@RequestMapping(path = "/api/order")
@CrossOrigin(origins = "http://localhost:4210")
public class PlaceOrderController {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PlaceOrderService orderService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/add")
	public ResponseEntity<MessageResponse> placeOrder(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) throws Exception{
		
		if (jwtTokenProvider.validateToken(AUTH_TOKEN)) {
			try {
				String loggedUserName = jwtTokenProvider.getUsername(AUTH_TOKEN);
				User loggedUser = userService.findByUserName(loggedUserName);
				this.orderService.saveOrder(loggedUser);
				this.cartService.deleteCartByEmail(loggedUser.getEmail());
			} catch (Exception e) {
				return ResponseEntity.ok(new MessageResponse("Cart Added Failed!"));
			}
		} else {
			throw new Exception("Token not Valid");
		}
		return ResponseEntity.ok(new MessageResponse("Cart Added Successfully!"));
	}
}
