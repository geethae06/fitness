package com.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeelogin")
public class EmployeeLogin {
	
	@Id
	@Column(name = "StaffId")
	private int staffId;
		
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	

	
		
}
