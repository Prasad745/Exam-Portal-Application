package com.prasad.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.Role;
import com.prasad.exam.model.User;
import com.prasad.exam.model.UserRole;
import com.prasad.exam.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserServiceImpl serviceImpl;
	
	@Autowired
	PasswordEncoder password;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
		
		ResponseDto res = new ResponseDto();
		
		try {
			
			user.setProfile("default.png");
			
			// encoding password with bcryptpasswordEncoder
			
//			user.setPassword(password.encode(user.getPassword()));
			
			Set<UserRole> roles = new HashSet<>();

			Role role = new Role();
			role.setRoleName("USER");

			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);

			roles.add(userRole);

			User saveUser = this.serviceImpl.createUser(user, roles);

//			logger.info("USER SAVE TO DB: " + saveUser);
			return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("INTERNAL SERVER ERROR"+ e.getMessage());
		}
		return null;
	}


	@GetMapping("/{userName}")
	public ResponseEntity<ResponseDto> getUserByuserName(@PathVariable("userName") String userName) {		
		ResponseEntity<ResponseDto> getUsername= serviceImpl.getUserByuserName(userName);
		return getUsername;
	}


	@DeleteMapping("/{id}")
	public void deleteByUserId(@PathVariable("id") long id) {
		this.serviceImpl.deleteUserById(id);
	}







}
