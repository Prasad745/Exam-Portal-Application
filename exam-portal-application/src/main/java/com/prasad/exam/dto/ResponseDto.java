package com.prasad.exam.dto;

import java.util.List;

import com.prasad.exam.model.Category;
import com.prasad.exam.model.Question;
import com.prasad.exam.model.Quiz;
import com.prasad.exam.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseDto {

	private boolean result;
	private int sttausCode;
	private String reason;
	private String jwtToken;
	private UserDto userInfo;
	private String message;
	private Long totalNoOfRecords;
	
	private User user;
	private Category category;
	private Question question;
	private Quiz quiz;

	private List<Category> categoryList;
	private List<Quiz> quizList;
	private List<Question> listOfQuestions;
}

