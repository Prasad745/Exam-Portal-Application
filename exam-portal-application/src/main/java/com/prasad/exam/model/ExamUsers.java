package com.prasad.exam.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@Entity
@Table(name = "exam_users")
@AllArgsConstructor @NoArgsConstructor
public class ExamUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;

	private String appName;


	private String password;

	private Date tokanStartTime;

	private Date updatedDate;

	private Date lastLoginTime;

	private String  lastTokenGenerated;

	private Long sessionActive;

	private String logResponse;
	
	private String empId;
	
    private String role;

	public ExamUsers(String userName, String password, Date tokanStartTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.tokanStartTime = tokanStartTime;
	}

}
