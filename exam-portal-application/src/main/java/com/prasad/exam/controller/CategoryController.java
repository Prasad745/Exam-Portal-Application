package com.prasad.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.Category;
import com.prasad.exam.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveCategory(@RequestBody Category category) {
		Category addCategory = this.categoryService.addCategory(category);
		return ResponseEntity.ok(addCategory);
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId){
		Category category = this.categoryService.getCategory(categoryId);
		return category;
	}
	
	@GetMapping("/getCatById/{categoryId}")
	public ResponseEntity<ResponseDto> getCategoryById(@PathVariable("categoryId") Long categoryId){
		ResponseEntity<ResponseDto> category = categoryService.categoryFindById(categoryId);
		return category;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllCategory(){
		List<Category> listOfCategory = this.categoryService.getCategories();
		return new ResponseEntity<>(listOfCategory, HttpStatus.OK);
	}
	
	@GetMapping("/categoryList")
	public ResponseEntity<ResponseDto> getAllCategoriesList(){
		ResponseEntity<ResponseDto> categoryList = categoryService.getCategorieLists();
		return categoryList;
	}
	
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		Category updateCategory = this.categoryService.updateCategory(category);
		return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		System.out.println("Deleted Category with ID is:"+categoryId);
		categoryService.deleteCategory(categoryId);
	}
	
}
