package com.prasad.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.exam.model.Category;
import com.prasad.exam.model.Quiz;
import com.prasad.exam.repository.QuizRepo;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepo quizRepo;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		Quiz saveQuiz =quizRepo.save(quiz);
		return saveQuiz;
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		Quiz updateExisting = this.getQuiz(quiz.getQId());
		updateExisting.setDescription(quiz.getDescription());
		updateExisting.setMaxMarks(quiz.getMaxMarks());
		updateExisting.setNumberOfQuestions(quiz.getNumberOfQuestions());
		updateExisting.setTitle(quiz.getTitle());
		updateExisting.setActive(quiz.getActive());
		
//		updateExisting.setCategory(quiz.getCategory());
//		updateExisting.setQuestions(quiz.getQuestions());
		
		Quiz saveQuiz = quizRepo.save(updateExisting);
		return saveQuiz;
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		List<Quiz> allQuizzes = quizRepo.findAll();
		return allQuizzes;
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return quizRepo.findByQuizId(quizId);
	}

	@Override
	public void deleteQuiz(Long quizId) {
//		Quiz quiz =  new Quiz();
//		quiz.setQId(quizId);
//		this.quizRepo.delete(quiz);
		this.quizRepo.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		
		List<Quiz> listQuizOfCatgeory = this.quizRepo.findBycategory(category);
		return listQuizOfCatgeory;
		
	}

	// get active quizzes
	
	@Override
	public List<Quiz> getActiveQuizzes() {
		List<Quiz> getActiveQuiz= quizRepo.findByActive(true);
		return getActiveQuiz;
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
		List<Quiz> getCateAndQuizzes = quizRepo.findByCategoryAndActive(category, true);
		return getCateAndQuizzes;
	}


}
