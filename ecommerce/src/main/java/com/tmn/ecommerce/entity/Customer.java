package com.tmn.ecommerce.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private UUID uuid;
	@Column(nullable = false)
	private String facebook_id;
	@Column(nullable = false)
	private String firebase_id;
	@Column(nullable = false)
	@Size(max = 50)
	private String name;

	@Embedded
	private Address address;

	@Column(nullable = false)
	@Size(max = 50)
	private String email;
	@Column(nullable = false)
	@Size(max = 20)
	private String phone_no;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "customers")
	private Set<Cart> carts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getFacebook_id() {
		return facebook_id;
	}

	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}

	public String getFirebase_id() {
		return firebase_id;
	}

	public void setFirebase_id(String firebase_id) {
		this.firebase_id = firebase_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", uuid=" + uuid + ", facebook_id=" + facebook_id + ", firebase_id=" + firebase_id
				+ ", name=" + name + ", address=" + address + ", email=" + email + ", phone_no=" + phone_no + ", carts="
				+ carts + "]";
	}

}
