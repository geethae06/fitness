package com.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.infy.service.SignUpService;
import com.fitness.models.EmployeeLogin;

@RestController
public class SignUpController {
	

	@Autowired
	SignUpService service;
	
	@PostMapping("/employee")
	public String registerUser(@RequestBody EmployeeLogin user) {		
		return service.registerEmployee(user);
	}
	
	@GetMapping("/employee")
	public List<EmployeeLogin> getEmployees() {
		return service.getEmployees();
	}
	
	@GetMapping("/employee/id/{id}")
	public EmployeeLogin getEmployeeById(@PathVariable Integer id) throws Exception {
		return service.getEmployeeById(id);
	}
	
	@PutMapping("/employee")
	public String updateEmployee(@RequestBody EmployeeLogin user) {	
		return service.updateEmployee(user);
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployeeById(@PathVariable Integer id) {
		return service.deleteEmployeeById(id);
	}
		
	@PostMapping("/employee/authenticate/{username}/{password}")
	public boolean authenticate(@PathVariable String username,@PathVariable String password) {		
		return service.authenticateUser(username,password);
	}
}
