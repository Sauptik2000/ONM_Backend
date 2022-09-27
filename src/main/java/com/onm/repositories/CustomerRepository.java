package com.onm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onm.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("select u from Customer u where u.customerEmail=?1")
	Customer findByCustomerEmail(String user);
}
