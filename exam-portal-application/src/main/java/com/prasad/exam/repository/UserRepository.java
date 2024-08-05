package com.prasad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.exam.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserName(String userName);
	
}
