package com.prasad.exam.security;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prasad.exam.model.User;
import com.prasad.exam.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Logger logger;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUserName(username);
		
		if(user == null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("No User Found !!");
		}
		
		logger.info("USER NAME IS FOUND IN DB :" + user.getUsername());
		
		System.out.println("USER DETAILS: " + user);
		
		return user;
	}

	
}
