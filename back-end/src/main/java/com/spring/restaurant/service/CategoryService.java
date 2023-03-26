package com.spring.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.model.Category;
import com.spring.restaurant.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}
	
	public List<Category> allCategories() {
		return categoryRepository.findAll();
	}
	
	

}
