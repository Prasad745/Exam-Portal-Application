package com.prasad.exam.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.Category;

@Service
public interface CategoryService {
	// add category
	public Category addCategory(Category category);
	
	// update catgeory
	public Category updateCategory(Category category);
	
	// get all catgeory
	public List<Category> getCategories();
	public ResponseEntity<ResponseDto> getCategorieLists();
	
	// get category by id
	public Category getCategory(Long categoryId);
	public ResponseEntity<ResponseDto> categoryFindById(Long categoryId);
	
	public void deleteCategory(Long categoryId);
}
