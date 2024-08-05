package com.prasad.exam.helper;

public class UserFoundException  extends Exception{

	
	public UserFoundException() {
		super("USER with this Username not found in Database !!");
	}
	
	public UserFoundException(String msg) {
		super(msg);
	}
	
}
