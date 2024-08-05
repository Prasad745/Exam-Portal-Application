package com.prasad.exam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter @Setter
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qId;

	private String title;

	private String description;

	private String maxMarks;
	private String numberOfQuestions;
	private Boolean active = false;
	
	// mapping many quiz has one category 
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	// mapping - one quiz has many questions
	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Question> questions = new ArrayList<>();

	public Quiz() {

	}
}
