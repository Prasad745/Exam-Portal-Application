package com.prasad.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prasad.exam.model.Question;
import com.prasad.exam.model.Quiz;

public interface QuestionRepo extends JpaRepository<Question, Long>{

	@Query("SELECT q FROM Question q WHERE q.quesId=:quesId")
	public Question findByQuestionId(@Param("quesId") Long questionId);

	public List<Question> findByQuiz(Quiz quiz);
	
}
