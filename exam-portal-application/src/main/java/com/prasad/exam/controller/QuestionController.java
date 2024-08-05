package com.prasad.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

import com.prasad.exam.model.Question;
import com.prasad.exam.model.Quiz;
import com.prasad.exam.service.QuestionService;
import com.prasad.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		Question saveQuestion = this.questionService.addQuestion(question);
		return ResponseEntity.ok(saveQuestion);
	}

	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}

	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		Question updateQuestion = this.questionService.updateQuestion(question);
		return new ResponseEntity<Question>(updateQuestion, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Question>> getQuestionOfQuiz(){
		List<Question> allQuestion = this.questionService.getAllQuestions();
		return new ResponseEntity<List<Question>>(allQuestion, HttpStatus.OK);
	}

	// get All question of any quiz ID
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid){
		//		Quiz quiz = new Quiz();
		//		quiz.setQId(qid);
		//		List<Question> getQuestionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		//		return ResponseEntity.ok(getQuestionOfQuiz);

		Quiz quiz = this.quizService.getQuiz(qid);
		List<Question> questions = quiz.getQuestions();

		List<Question> list = new ArrayList(questions);
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});

		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	// get All question of any quiz ID
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qid") Long qid){
		Quiz quiz = new Quiz();
		quiz.setQId(qid);
		List<Question> getQuestionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(getQuestionOfQuiz);
	}

	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}


	// eval Quiz
	@PostMapping("/evalQuiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		System.out.println(questions);

		double marksGet=0;
		int correctAnswers=0;
		int attempted=0;

		for (Question q: questions) {
		
			// single questions
			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().equals(q.getGivenAnswer())) {				
				//correct
				correctAnswers++;
		
				Double marksSingle =Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGet+=marksSingle;
			}

			if(q.getGivenAnswer() !=null) {
				attempted++;
			}
		}
		Map<Object, Object> map = Map.of("marksGet",marksGet,"correctAnswers",correctAnswers,"attempted",attempted);
		for (Question question : questions) {
			System.out.println(question);
		}
		return ResponseEntity.ok(map);
	}




}
