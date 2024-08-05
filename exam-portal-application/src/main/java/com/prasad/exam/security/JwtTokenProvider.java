package com.prasad.exam.security;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.prasad.exam.model.ExamUsers;
import com.prasad.exam.repository.ExamUsersRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	@Autowired
	ExamUsersRepo usersRepo;

	@SuppressWarnings("unused")
	public String generateToken(ExamUsers authentication) {

//		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

//		CCSUsers user=userRepo.findByEmpIdAndAppName(authentication.getUserName(),CCSalesConstants.appName);

		ExamUsers user = usersRepo.findByUserName(authentication.getUserName());
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		Claims claims = Jwts.claims().setSubject(Long.toString(user.getId())).setExpiration(expiryDate)
				.setIssuedAt(new Date());
		/*
		 * Consult on this ** IMPORTANT ** claims.put("userId",
		 * String.valueOf(jwtUser.getId()));
		 */
		claims.put("userId", String.valueOf(user.getId()));
		claims.put("generatedDateTime", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
//        claims.put("role", jwtUser.getRole());

		return Jwts.builder().setClaims(claims)
				// use for expire token
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}
