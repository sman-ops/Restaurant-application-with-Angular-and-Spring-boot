package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.model.Category;
import com.spring.restaurant.service.CategoryService;

@RestController
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService ) {
		this.categoryService=categoryService;
	}
	
	@GetMapping("/api/allCategories")
	public  List<Category> getAllCategory(){
		
	return categoryService.allCategories();
		
		
	}
}
