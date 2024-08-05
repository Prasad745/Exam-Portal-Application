package com.prasad.exam.service;

import java.util.List;

import com.prasad.exam.model.Category;
import com.prasad.exam.model.Quiz;

public interface QuizService {

	// add Quiz
	public Quiz addQuiz(Quiz quiz);
	
	// update Quiz
	public Quiz updateQuiz(Quiz quiz);
	
	// get All Quizzes
	public List<Quiz> getAllQuizzes();
	
	// get Quiz By ID
	public Quiz getQuiz(Long quizId);
	
	// delete Quiz By ID
	public void deleteQuiz(Long quizId);
	
	public List<Quiz> getQuizzesOfCategory(Category category);
	
	// check Which User is Active and It's Category
	
	public List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuizzesOfCategory(Category category);
}
