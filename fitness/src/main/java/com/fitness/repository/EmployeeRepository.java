package com.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitness.models.EmployeeLogin;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeLogin, Integer> {

	@Query("select e from EmployeeLogin e where e.username =:uname")
	public EmployeeLogin findByUnameAndPwd(@Param("uname") String uname);
}
