package com.prasad.exam.dto;

public class JwtResponse {

	String jwtToken;

	public JwtResponse() {		
	}

	public JwtResponse(String jwtToken) {
		this.jwtToken=jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

}
