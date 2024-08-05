package com.prasad.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExamPortalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}
	
	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(this.getClass());
	}
	
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Starting code");
//		
//		User user = new User();
//		
//		user.setUsername("Arjun");
//		user.setPassword("arjun");
//		user.setEmail("arjun@gmail.com");
//		user.setCountry("India");
//		user.setPhoneNumber("9702234085");
//		user.setProfile("default.png");
//		
//		Role role = new Role();
//		
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> userRolesSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		
//		userRolesSet.add(userRole);
//	
//		User saveUser = this.userService.createUser(user, userRolesSet);
//		System.out.println(saveUser.getUsername());
//	}
}
