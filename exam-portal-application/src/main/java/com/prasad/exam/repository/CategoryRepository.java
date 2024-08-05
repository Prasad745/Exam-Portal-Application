package com.prasad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prasad.exam.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("SELECT c FROM Category c WHERE c.cid=:cid")
	public Category findByCategoryId(@Param("cid") Long categoryId);


	@Query(value = "DELETE FROM category WHERE cid = ?1", nativeQuery = true)
	public void deleteByCategoryId(@Param("cid") Long categoryId);
}
