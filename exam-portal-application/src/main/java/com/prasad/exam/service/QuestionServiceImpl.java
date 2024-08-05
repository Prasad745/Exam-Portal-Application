package com.prasad.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.exam.model.Question;
import com.prasad.exam.model.Quiz;
import com.prasad.exam.repository.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepo questionRepo;
	
	@Override
	public Question addQuestion(Question question) {
		return questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		Question existingUpdate = this.getQuestion(question.getQuesId());
		existingUpdate.setContent(question.getContent());
		existingUpdate.setAnswer(question.getAnswer());
		existingUpdate.setImage(question.getImage());
		existingUpdate.setOption1(question.getOption1());
		existingUpdate.setOption2(question.getOption2());
		existingUpdate.setOption3(question.getOption3());
		existingUpdate.setOption4(question.getOption4());
		existingUpdate.setOption1(question.getOption1());
		existingUpdate.setOption1(question.getOption1());
//		existingUpdate.setQuiz(question.getQuiz());
		
		Question updateQuestion = questionRepo.save(existingUpdate);
		return updateQuestion;
	}

	@Override
	public List<Question> getAllQuestions() {
		return this.questionRepo.findAll();
	}

	@Override
	public Question getQuestion(Long questionId) {
		Question question = questionRepo.findByQuestionId(questionId);
		return question;
	}

	@Override
	public void deleteQuestion(Long questionId) {
//		Question question = new Question();
//		question.setQuesId(questionId);
//		this.questionRepo.delete(question);
		this.questionRepo.deleteById(questionId);
	}

	@Override
	public List<Question> getQuestionOfQuiz(Quiz quiz) {
		return this.questionRepo.findByQuiz(quiz);
	}

	@Override
	public Question get(Long questionsId) {
		return this.questionRepo.getOne(questionsId);
	}

}
