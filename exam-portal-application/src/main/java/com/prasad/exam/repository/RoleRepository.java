package com.prasad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prasad.exam.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(value="SELECT DISTINCT r.roleName from Role r WHERE r.userName=:userName and r.empId=:empId")
	public String findByUserRole(String userName, String empId);
	
//	@Query(value="SELECT DISTINCT role_name from roles r WHERE r.userName=:user_name and r.empId=:emp_id", nativeQuery = true)
//	public String findByUserRole(String userName, String empId);

}
