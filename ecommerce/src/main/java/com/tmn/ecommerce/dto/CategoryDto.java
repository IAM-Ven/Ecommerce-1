package com.tmn.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.tmn.ecommerce.entity.Category;

public class CategoryDto {

	Long id;
	@NotNull
	String name;
	
	public CategoryDto() {	}
	
	public CategoryDto(Category cat) {
		this.id = cat.getId();
		this.name = cat.getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Category getEntity() {
		Category cat = new Category();
		cat.setId(this.id);
		cat.setName(this.name);
		return cat;
	}


	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", name=" + name + "]";
	}
	
}