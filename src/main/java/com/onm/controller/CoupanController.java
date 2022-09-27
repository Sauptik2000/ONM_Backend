package com.onm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onm.entity.Coupan;
import com.onm.services.CoupanServices;

@RestController
@CrossOrigin
public class CoupanController {
	@Autowired
	 CoupanServices coupanServices;
	
	@PostMapping("/addCoupan/{customerId}")
	public Boolean addCoupan(@RequestBody Coupan coupan,@PathVariable int customerId) {
		coupanServices.addCoupan(coupan,customerId);
		return true;
	}
	
	@GetMapping("/viewCoupan")
	public List viewCoupan() {
		return coupanServices.viewCoupan();
	}
	
	@DeleteMapping("/deleteCoupan/{coupanId}")
	public Boolean deleteCoupan(@PathVariable int coupanId) 
	{
    	System.out.println(coupanId);
    	coupanServices.deleteCoupan(coupanId);
		//return ResponseEntity.status(HttpStatus.OK).body("Coupan Deleted");
    	return true;
	}
}
