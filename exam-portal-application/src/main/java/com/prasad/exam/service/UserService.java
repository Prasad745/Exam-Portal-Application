package com.prasad.exam.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.User;
import com.prasad.exam.model.UserRole;

public interface UserService {

	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	public ResponseEntity<ResponseDto> getUserByuserName(String userName);
	public String deleteUserById(long id);
	
}
