package com.prasad.exam.repository;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prasad.exam.model.ExamUsers;

public interface ExamUsersRepo extends JpaRepository<ExamUsers, Long>{

	public ExamUsers findByEmpId(String empId);
	
	@Modifying
	@Transactional
	@Query("Update ExamUsers set tokanStartTime=:timestamp,password=:encode,lastLoginTime=:timestamp,role=:role ,empId=:empId,logResponse=:newJsonDataOfQues where userName=:userName ")
	void updatePassword(@Param("timestamp")  Timestamp timestamp,@Param("encode")  String encode,@Param("userName")  String userName,@Param("role")  String role, String empId,@Param("newJsonDataOfQues")  String newJsonDataOfQues);

	
	ExamUsers findByUserName(String userName);
	
	@Modifying
	@Transactional
	@Query("Update ExamUsers set sessionActive=:sessionActive,lastTokenGenerated=:lastTokenGenerated  where userName=:userName ")
	void updateToken(@Param("userName") String userName,@Param("lastTokenGenerated")  String lastTokenGenerated,@Param("sessionActive")  Long sessionActive);
}
