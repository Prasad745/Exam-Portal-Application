package com.prasad.exam.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.Category;
import com.prasad.exam.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private Logger logger;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		Category cat = this.getCategory(category.getCid());
		cat.setDescription(category.getDescription());
		cat.setTitle(category.getTitle());
//		cat.setQuizzes(category.getQuizzes());
		
		Category saveCategory = categoryRepository.save(cat);
		return saveCategory;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> getAllCategories = categoryRepository.findAll();
		return getAllCategories;
	}
	
	@Override
	public ResponseEntity<ResponseDto> getCategorieLists() {
		ResponseDto res = new ResponseDto();
		try {
			List<Category> categoryList = categoryRepository.findAll();
			if(categoryList !=null && categoryList.size() > 0) {
				logger.info("CATEGORY LIST");
				for (Category category : categoryList) {
					System.out.println(category);
				}
				res.setResult(true);
				res.setSttausCode(200);
				res.setMessage("CategoryList Getting Successfully");
				res.setCategoryList(categoryList);
				return ResponseEntity.ok().body(res);
			}else {
				res.setResult(false);
				res.setMessage("Category List is not found");
				return ResponseEntity.ok().body(res);
			}
			
		} catch (Exception e) {
			logger.error("ERROR WHILE GETTING RESPONSE LIST", e);
			res.setMessage(e.getMessage());
			res.setResult(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
	

	@Override
	public Category getCategory(Long categoryId) {
		Category category = categoryRepository.findByCategoryId(categoryId);
		return category;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = getCategory(categoryId);
		
		if(category.getCid() == categoryId) {
		  	categoryRepository.deleteByCategoryId(categoryId);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> categoryFindById(Long categoryId) {
		ResponseDto res = new ResponseDto();
		
		try {
			Category category = categoryRepository.findByCategoryId(categoryId);
			if(category.getCid() !=null) {
				logger.info("Get Category By CategoryId");
				res.setResult(true);
				res.setSttausCode(200);
				res.setMessage("Done");
				res.setCategory(category);
				return ResponseEntity.ok().body(res);
			}else {
				res.setResult(false);
				res.setSttausCode(400);
				res.setMessage("Category Is Not Found By CategoryId");
				res.setCategory(category);
				return ResponseEntity.ok().body(res);
			}
		} catch (Exception e) {
			logger.error("ERROR WHILE GETTING CATEGORY IS NOT FOUND BY CATEGORYID", e);
			res.setMessage(e.getMessage());
			res.setResult(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}

}
