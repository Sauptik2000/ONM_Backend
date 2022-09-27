package com.onm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onm.entity.Customer;
import com.onm.expections.ResourceAlreadyExistException;
import com.onm.expections.ResourceNotFoundException;
import com.onm.repositories.CustomerRepository;

@Service
public class LoginServices {
    @Autowired
    CustomerRepository customerRepository;
    
    public boolean register(Customer customer) {
        Customer cust=customerRepository.findByCustomerEmail(customer.getCustomerEmail());
        if(cust==null){
            customer.setCustomerType("user");
            customerRepository.save(customer);
            return true;
        }
        else{
            
            throw new ResourceAlreadyExistException("User Aleady exist");
        }
    }
    
    public Customer login(Customer customer) {

        Customer cust=customerRepository.findByCustomerEmail(customer.getCustomerEmail());
//        System.out.println(cust);
        if(cust==null){
            throw new ResourceNotFoundException("User not found ");
        }
        else{
            if(cust.getPassword().equals(customer.getPassword())){
                cust.setLoginStatus(true);
                customerRepository.save(cust);
                return cust;
            }
        }

        throw new ResourceNotFoundException("Password missmatched ");
    }
    
	public void updateCustomer(Customer customer) 
	{
		if(customerRepository.findById(customer.getCustomerId()).isPresent())
		{
			Customer c=customerRepository.findById(customer.getCustomerId()).get();
			c.setCustomerName(customer.getCustomerName());
			//c.setCustomerAddress(customer.getCustomerAddress());
			//c.setCustomerMobile(customer.getCustomerMobile());
			customerRepository.save(c);
		}
	}
	
	public List viewCustomerList() 
	{
		return customerRepository.findAll();
	}
    
}