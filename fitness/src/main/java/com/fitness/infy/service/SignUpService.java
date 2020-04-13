package com.fitness.infy.service;

import java.util.List;

import com.fitness.models.EmployeeSignup;

public interface SignUpService {

		public String registerEmployee(EmployeeSignup user);
	
		public List<EmployeeSignup> getEmployees();

		public EmployeeSignup getEmployeeById(Integer id) throws Exception;
		
		public String updateEmployee(EmployeeSignup employee);

		public String deleteEmployeeById(Integer id);
		
		public boolean authenticateUser(int staffId, String password);
}
