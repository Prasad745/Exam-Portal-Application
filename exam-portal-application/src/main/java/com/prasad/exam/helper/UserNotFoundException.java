package com.prasad.exam.helper;

public class UserNotFoundException  extends Exception{

	
	public UserNotFoundException() {
		super("USER with this Username not found in Database !!");
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
	
}
