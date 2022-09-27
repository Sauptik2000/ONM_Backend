package com.onm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
	@Id
	@SequenceGenerator(
	           name ="user_sequence",
	           sequenceName = "user_sequence",
	           allocationSize = 1
	   )
	@GeneratedValue(
	           strategy = GenerationType.SEQUENCE,
	           generator = "user_sequence"
	   )
    private int customerId;
	private String customerName;
	private String customerEmail;
	private String customerType;
	private boolean loginStatus;
	private String password;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	

	public Customer() {
		super();
	}

	

	public Customer(String customerName, String customerEmail, String password) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.password = password;
	}
	
	
}
