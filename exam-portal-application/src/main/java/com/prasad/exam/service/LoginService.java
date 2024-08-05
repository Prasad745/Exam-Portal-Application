package com.prasad.exam.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.ExamUsers;

public interface LoginService {

	ResponseEntity<ResponseDto> login(ExamUsers users, HttpServletRequest request);

	Boolean invalidateUserToken(String token);
}
