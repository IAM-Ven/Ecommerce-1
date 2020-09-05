package com.tmn.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmn.ecommerce.dto.CategoryResponse;
import com.tmn.ecommerce.entity.Category;
import com.tmn.ecommerce.service.CategoryService;

@RestController
@RequestMapping(path = "/api/cat")
@CrossOrigin(origins = "http://localhost:4210")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> getAllCategories(){
		CategoryResponse resp = new CategoryResponse();
		try {
			resp.setStatus(HttpStatus.OK.toString());
			resp.setMessage("Category List");
			resp.setOblist(categoryService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage(e.getMessage());
		}	
		return new ResponseEntity<CategoryResponse>(resp,HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody Category cat) {
		CategoryResponse resp = new CategoryResponse();
		try {
			if (categoryService.existsByName(cat.getName())) {
				resp.setStatus(HttpStatus.BAD_REQUEST.toString());
				resp.setMessage("Category Already Exists!");
			} else {
				this.categoryService.createCategory(cat);
				resp.setStatus(HttpStatus.OK.toString());
				resp.setMessage("Category Saved Successfully!");
			}
			resp.setOblist(categoryService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage(e.getMessage());
		}	
		return new ResponseEntity<CategoryResponse>(resp,HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody Category cat) {
		CategoryResponse resp = new CategoryResponse();
		try {
			if (categoryService.existsByName(cat.getName())) {
				resp.setStatus(HttpStatus.BAD_REQUEST.toString());
				resp.setMessage("Category Already Exists!");
			} else {
				this.categoryService.createCategory(cat);
				resp.setStatus(HttpStatus.OK.toString());
				resp.setMessage("Category Updated Successfully!");
			}
			resp.setOblist(categoryService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage(e.getMessage());
		}
		return new ResponseEntity<CategoryResponse>(resp,HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable("id") Long id) {
		CategoryResponse resp = new CategoryResponse();
        try {
        	this.categoryService.deleteCategoryById(id);
			resp.setStatus(HttpStatus.OK.toString());
			resp.setMessage("Category Deleted Successfully");
			resp.setOblist(categoryService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage("This category has items");
		} 
        return new ResponseEntity<CategoryResponse>(resp,HttpStatus.OK);
	}
	
}
