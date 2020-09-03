package com.tmn.ecommerce.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tmn.ecommerce.dto.ItemDto;

@Entity
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uuid;
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	@Size(max = 50)
	private String name;
	private double price;
	private String item_code;
	private String description;
	private int quantity;
	private String size;
	private String color;
	
	@ManyToMany(mappedBy = "items")
	private List<Cart> carts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "item")
	private List<ItemPhoto> photos; 
	
	public Item() {
	}
	
	public Item(ItemDto dto){
		this.name = dto.getName();
		this.price = dto.getPrice();
		this.description = dto.getDescription();
		this.quantity = dto.getQuantity();
		this.size = dto.getSize();
		this.color = dto.getColor();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		return "Item [id=" + id + ", uuid=" + uuid + ", category=" + category + ", name=" + name + ", price=" + price
				+ ", item_code=" + item_code + ", description=" + description + ", quantity=" + quantity + ", size="
				+ size + ", color=" + color + ", carts=" + carts + ", photos=" + photos + "]";
	}

}
