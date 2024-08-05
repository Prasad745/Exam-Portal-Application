package com.prasad.exam.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.exam.dto.JwtRequest;
import com.prasad.exam.dto.JwtResponse;
import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.model.ExamUsers;
import com.prasad.exam.model.User;
import com.prasad.exam.security.JwtUtil;
import com.prasad.exam.security.MyUserDetailsService;
import com.prasad.exam.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	// Get Current User
	@GetMapping(value = "/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
	}

	@PostMapping("/user/login")
	public ResponseEntity<ResponseDto> login(@RequestBody ExamUsers users, HttpServletRequest request) {
		return loginService.login(users, request);
	}
	
	@PostMapping(value = "/generate-token")
	public ResponseEntity<?> GenerateAuthToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
					);
			 
		}catch (DisabledException e) {
			throw new Exception("USER DISABLED " + e.getMessage());
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials !! " + e.getMessage()); 
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		System.out.println("JWT TOKEN:- "+jwt);
		
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
	
}
