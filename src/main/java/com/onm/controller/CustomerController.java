package com.onm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onm.entity.Customer;
import com.onm.services.LoginServices;

@RestController
@CrossOrigin
public class CustomerController {
	 @Autowired
	    LoginServices loginservices;
	 
	 private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	 
	 @GetMapping(value="/")
		public  String add(){
	        log.info("hello");
	        return "Hello World";
	    }
	 
	 @PostMapping("/register")	
	    public ResponseEntity register(@RequestBody Customer customer) 
	    {
	    	if(loginservices.register(customer)) {
	    		return ResponseEntity.status(HttpStatus.OK).body(customer);
	    	}
	    	return ResponseEntity.status(HttpStatus.FOUND).body("customer already exist");
	    }
	 
	 @PostMapping("/login")	
	    public ResponseEntity login(@RequestBody Customer customer) 
	    {
	       return ResponseEntity.status(HttpStatus.OK).body(loginservices.login(customer));
	    }
}
