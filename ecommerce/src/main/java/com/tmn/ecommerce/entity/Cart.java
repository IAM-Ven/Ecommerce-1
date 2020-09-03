package com.tmn.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uuId;
	private String name; 
	private int quantity;
	private String phoneNo;
	private double price;
	//private List<String> image_Name;
	private String size;
	private String color;
	
	
	@Temporal(TemporalType.DATE)
	private Date created_date;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_item",
			joinColumns =  @JoinColumn(name = "cart_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "item_id",referencedColumnName = "id"))
	private Set<Item> items;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_customer",
			joinColumns =  @JoinColumn(name = "cart_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "customer_id",referencedColumnName = "id"))
	private Set<Customer> customers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", uuId=" + uuId + ", name=" + name + ", quantity=" + quantity + ", phoneNo="
				+ phoneNo + ", price=" + price + ", size=" + size + ", color=" + color + ", created_date="
				+ created_date + ", items=" + items + "]";
	}
	
}
