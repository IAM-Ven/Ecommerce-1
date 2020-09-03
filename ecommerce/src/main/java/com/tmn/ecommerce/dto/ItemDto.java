package com.tmn.ecommerce.dto;

import java.util.List;

import com.tmn.ecommerce.entity.Cart;
import com.tmn.ecommerce.entity.ItemPhoto;

public class ItemDto {

	private long id;
    private long category_id;
	private String name;
	private double price;
	private String item_code;
	private String description;
	private int quantity;
	private String size;
	private String color;
	
	private List<Cart> carts;
	
	private List<ItemPhoto> photos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<ItemPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<ItemPhoto> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "ItemDto [id=" + id + ", category_id=" + category_id + ", name=" + name + ", price=" + price
				+ ", item_code=" + item_code + ", description=" + description + ", quantity=" + quantity + ", size="
				+ size + ", color=" + color + ", carts=" + carts + ", photos=" + photos + "]";
	} 
	
}
