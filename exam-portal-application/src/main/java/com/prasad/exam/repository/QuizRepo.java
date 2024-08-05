package com.prasad.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prasad.exam.model.Category;
import com.prasad.exam.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long>{

	@Query("SELECT q FROM Quiz q WHERE q.qId=:qId")
	public Quiz findByQuizId(@Param("qId") Long quizId);
	
	public List<Quiz> findBycategory(Category category);
	
	// find by Active User and it's category
	
	public List<Quiz> findByActive(Boolean userIsActive);
	public List<Quiz> findByCategoryAndActive(Category category, Boolean userIsActive);
}
