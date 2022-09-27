package com.onm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onm.entity.Coupan;
import com.onm.expections.NotAdminException;
import com.onm.expections.ResourceNotFoundException;
import com.onm.repositories.CoupanRepository;
import com.onm.repositories.CustomerRepository;

@Service
public class CoupanServices {
	 @Autowired
	 CoupanRepository coupanRepo;
	 @Autowired
	 CustomerRepository customerRepo;
	 
	 public boolean addCoupan(Coupan coupan, int customerId) {
		 com.onm.entity.Customer c = customerRepo.findById(customerId).get();
		 if(c.getCustomerType().equals("user")) {
			 throw new NotAdminException("Access Denied");
		 }else {
			 coupanRepo.save(coupan);
			 return true;
		 }
	 }
	 
	 public List viewCoupan() {
		 List<Coupan> coupans = coupanRepo.findAll();
		 return coupans;
	 }
	 
	 public void deleteCoupan(int coupanId) {
		 Coupan cp = coupanRepo.findById(coupanId).get();
		 if(cp== null) {
			 throw new ResourceNotFoundException("Coupan not found ");
		 }else {
			 coupanRepo.deleteById(coupanId);
		 }
	 }
}
