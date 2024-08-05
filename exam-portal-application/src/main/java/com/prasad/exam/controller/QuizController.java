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

import com.prasad.exam.model.Category;
import com.prasad.exam.model.Quiz;
import com.prasad.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
		Quiz saveQuiz = this.quizService.addQuiz(quiz);		
		return ResponseEntity.ok(saveQuiz);
	}
	
	@GetMapping("/{qid}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("qid") Long qid) {
		Quiz getQuiz = this.quizService.getQuiz(qid);
		return new ResponseEntity<Quiz>(getQuiz, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		Quiz updateQuiz = this.quizService.updateQuiz(quiz);
		return new ResponseEntity<Quiz>(updateQuiz, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Quiz>> getAllQuizzes(){
		List<Quiz> getQuizzes = this.quizService.getAllQuizzes();
		
		return new ResponseEntity<List<Quiz>>(getQuizzes, HttpStatus.OK);
	}
	
	@DeleteMapping("/{qid}")
	public void deleteQuiz(@PathVariable("qid") Long qid) {
		this.quizService.deleteQuiz(qid);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid){
		
		Category category = new Category();
		category.setCid(cid);
		return quizService.getQuizzesOfCategory(category);
	}
	
	// get active quizzes
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return quizService.getActiveQuizzes();
	}
	
	// get active quizzes of category
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid){
		Category cat= new Category();
		cat.setCid(cid);
		return quizService.getActiveQuizzesOfCategory(cat);
	}
	
}
