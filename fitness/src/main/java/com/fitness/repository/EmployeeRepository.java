package com.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitness.models.EmployeeSignup;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeSignup, Integer> {

}
