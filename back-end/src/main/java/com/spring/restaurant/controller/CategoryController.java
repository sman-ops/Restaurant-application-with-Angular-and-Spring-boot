package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.Order;
import com.spring.restaurant.service.CategoryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/")
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService ) {
		this.categoryService=categoryService;
	}
	
	@GetMapping("allCategories")
	public  ResponseEntity<List<Category>> getAllCategory(){
		
		List<Category> categories= categoryService.allCategories();
		
		return new  ResponseEntity<>(categories,HttpStatus.OK);
		
		
	}
}
