package com.fitness.infy.service;

import java.util.List;

import com.fitness.models.EmployeeLogin;

public interface SignUpService {

		public String registerEmployee(EmployeeLogin user);
	
		public List<EmployeeLogin> getEmployees();

		public EmployeeLogin getEmployeeById(Integer id) throws Exception;
		
		public String updateEmployee(EmployeeLogin employee);

		public String deleteEmployeeById(Integer id);
		
		public boolean authenticateUser(String username, String password);
}
