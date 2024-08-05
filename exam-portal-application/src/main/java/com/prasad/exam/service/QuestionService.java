package com.prasad.exam.service;

import java.util.List;

import com.prasad.exam.model.Question;
import com.prasad.exam.model.Quiz;

public interface QuestionService {

	//add question
	public Question addQuestion(Question question);
	
	// update question
	public Question updateQuestion(Question question);
	
	// get All question
	public List<Question> getAllQuestions();
	
	// get question By ID
	public Question getQuestion(Long questionId);
	
	// delete question By ID
	public void deleteQuestion(Long questionId);
	
	public List<Question> getQuestionOfQuiz(Quiz quiz);
	
	public Question get(Long questionsId);
}
