package com.tmn.ecommerce.dto;

import java.util.List;

public class CartResponse {

	private String message;
	private List<CartDto> cartDtoList;
	
	public CartResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CartDto> getCartDtoList() {
		return cartDtoList;
	}

	public void setCartDtoList(List<CartDto> cartDtoList) {
		this.cartDtoList = cartDtoList;
	}
	
	
}
