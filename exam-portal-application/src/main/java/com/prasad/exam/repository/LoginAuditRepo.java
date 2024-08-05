package com.prasad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.exam.model.LoginAudit;

public interface LoginAuditRepo extends JpaRepository<LoginAudit, Long>{

}
