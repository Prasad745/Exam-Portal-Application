package com.prasad.exam.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@Entity
public class LoginAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;

	private String appName;


	private String type;

	private Date accessTime;

	@ManyToOne
	@JoinColumn(name="exam_users_id")
	private ExamUsers user;
	
	public LoginAudit(String userName,  Date accessTime, ExamUsers user) {
		super();
		this.userName = userName;
		this.accessTime = accessTime;
		this.user = user;
	}

}
