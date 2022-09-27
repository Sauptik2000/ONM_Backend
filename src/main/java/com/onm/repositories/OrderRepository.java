package com.onm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onm.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("select u from Order u where u.customerId=?1")
	List<Order> findByCustomerId(int customerId);
}
