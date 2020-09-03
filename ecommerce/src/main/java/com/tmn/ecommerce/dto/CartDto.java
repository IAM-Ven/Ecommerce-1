package com.tmn.ecommerce.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tmn.ecommerce.entity.Cart;

public class CartDto {

	private Long cartId;
	private String email;
	private int quantity;
	private double price;
	private Date dateAdded;
	
	public CartDto() {
	}
	
//	public CartDto(Cart cart) {
//		this.cartId = cart.getCartId();
//		this.email = cart.getEmail();
//		this.dateAdded = cart.getDateAdded();
//		this.quantity = cart.getQuantity();
//		this.price = cart.getPrice();
//	}
	
	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	@JsonIgnore
//	public Cart getEntity() {
//		Cart cart = new Cart();
//		cart.setCartId(this.cartId);
//		return cart;
//	}

	@Override
	public String toString() {
		return "CartDto [cartId=" + cartId + ", email=" + email + ", dateAdded=" + dateAdded
				+ ", quantity=" + quantity + ", price=" + price +  "]";
	}
	
}
