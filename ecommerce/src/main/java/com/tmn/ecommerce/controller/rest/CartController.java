package com.tmn.ecommerce.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmn.ecommerce.dto.CartDto;
import com.tmn.ecommerce.dto.MessageResponse;
import com.tmn.ecommerce.entity.User;
import com.tmn.ecommerce.security.JwtTokenProvider;
import com.tmn.ecommerce.service.CartService;
import com.tmn.ecommerce.service.ItemService;
import com.tmn.ecommerce.service.UserService;

@RestController
@RequestMapping(path = "/api/cart")
@CrossOrigin(origins = "http://localhost:4210")
public class CartController {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
//	@GetMapping("/add")
//	public ResponseEntity<MessageResponse> addToCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
//					@RequestParam(name="id") String id) throws Exception {
//		if (jwtTokenProvider.validateToken(AUTH_TOKEN)) {
//			try {
//				String loggedUserName = jwtTokenProvider.getUsername(AUTH_TOKEN);
//				User loggedUser = this.userService.findByUserName(loggedUserName);
//				System.out.println(loggedUser);
//				this.cartService.saveCart(id,loggedUser);
////				resp.setStatus(ResponseCode.SUCCESS_CODE);
////				resp.setMessage(ResponseCode.CART_UPD_MESSAGE_CODE);
////				resp.setAUTH_TOKEN(AUTH_TOKEN);
//			} catch (Exception e) {
////				resp.setStatus(ResponseCode.FAILURE_CODE);
//				return ResponseEntity.ok(new MessageResponse("Cart Added Failed"));
////				resp.setAUTH_TOKEN(AUTH_TOKEN);
//			}
//		}
//	else {
//		throw new Exception("Token not Valid");
////			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
//		}
//		
//		return ResponseEntity.ok(new MessageResponse("Cart Added successfully!"));
//	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> addToCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
			@RequestParam(name="id") String id) throws Exception {
		MessageResponse resp = new MessageResponse();
		if (jwtTokenProvider.validateToken(AUTH_TOKEN)) {
			try {
				String loggedUserName = jwtTokenProvider.getUsername(AUTH_TOKEN);
				User loggedUser = this.userService.findByUserName(loggedUserName);
				System.out.println(loggedUser);
				this.cartService.saveCart(id,loggedUser);
				resp.setStatus("200");
				resp.setMessage("Add to Cart successfully");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus("400");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			throw new Exception("Token not Valid");
				//resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<MessageResponse>(resp,HttpStatus.OK);
	} 
	
	@GetMapping(produces = "application/json")
	public List<CartDto> viewCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN)
													throws Exception {
		List<CartDto> cartList = null;
		if (jwtTokenProvider.validateToken(AUTH_TOKEN)) {
			try {
				String loggedUserName = jwtTokenProvider.getUsername(AUTH_TOKEN);
				User loggedUser = this.userService.findByUserName(loggedUserName);
				cartList = this.cartService.viewCart(loggedUser.getEmail());
//				resp.setStatus(ResponseCode.SUCCESS_CODE);
//				resp.setMessage(ResponseCode.CART_UPD_MESSAGE_CODE);
//				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
//				resp.setStatus(ResponseCode.FAILURE_CODE);
				//return ResponseEntity.ok(new CartResponse("Cart Show not OK"));
//				resp.setAUTH_TOKEN(AUTH_TOKEN);
				
			}
		}
	else {
		throw new Exception("Token not Valid");
//			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return cartList;
	}
	
	@GetMapping("/update")
	public List<CartDto> updateCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
			@RequestParam(name = "id") String cartid,
			@RequestParam(name = "quantity") String quantity) throws Exception {

		if (jwtTokenProvider.validateToken(AUTH_TOKEN)) {
			try {
				String loggedUserName = jwtTokenProvider.getUsername(AUTH_TOKEN);
				User loggedUser = this.userService.findByUserName(loggedUserName); 	
				this.cartService.searchCartByIdEmailQuantity(Long.parseLong(cartid),loggedUser.getEmail(),Integer.parseInt(quantity));
				List<CartDto> cartList = this.cartService.viewCart(loggedUser.getEmail());
				
				return cartList;
			} catch (Exception e) {
//				resp.setStatus(ResponseCode.FAILURE_CODE);
//				resp.setMessage(e.getMessage());
//				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return new ArrayList<CartDto>();
			}
		} else {
			//resp.setStatus(ResponseCode.FAILURE_CODE);
			//resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			throw new Exception("Token not Valid");
		}
	}
	
	@GetMapping("/delete")
	public List<CartDto> deleteCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
			@RequestParam(name = "id") String cartid) throws Exception {

		if (jwtTokenProvider.validateToken(AUTH_TOKEN)) {
			try {
				String loggedUserName = jwtTokenProvider.getUsername(AUTH_TOKEN);
				User loggedUser = this.userService.findByUserName(loggedUserName); 	
				this.cartService.deleteCartById(Long.parseLong(cartid),loggedUser.getEmail());
				List<CartDto> cartList = this.cartService.viewCart(loggedUser.getEmail());
				return cartList;
			} catch (Exception e) {
//				resp.setStatus(ResponseCode.FAILURE_CODE);
//				resp.setMessage(e.getMessage());
//				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return new ArrayList<CartDto>();
			}
		} else {
			//resp.setStatus(ResponseCode.FAILURE_CODE);
			//resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			throw new Exception("Token not Valid");
		}
	}
	
}
