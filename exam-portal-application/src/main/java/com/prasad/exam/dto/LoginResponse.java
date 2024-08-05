package com.prasad.exam.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class LoginResponse implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private String isSuccess;
	private String expiresOn;



}