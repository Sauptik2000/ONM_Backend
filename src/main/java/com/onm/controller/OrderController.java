package com.onm.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onm.entity.Order;
import com.onm.repositories.OrderRepository;
import com.onm.services.OrderServices;

@RestController
@CrossOrigin 
public class OrderController {
	@Autowired
	OrderServices orderServices;
	@Autowired
	OrderRepository orderRepo;
	
	//Function to BookOrder
	@PostMapping("/bookOrder/{customerId}/{plantId}/{coupanId}")
	public boolean bookOrder (@RequestBody Order order,@PathVariable int customerId, @PathVariable int plantId, @PathVariable int coupanId) 
	{
		order.setCoupanId(coupanId);
		orderServices.bookOrder(order, customerId, plantId);
//		return ResponseEntity.status(HttpStatus.OK).body("Plant Ordered");
		return true;
	}
	
	//Function to ViewOrder
	@GetMapping("/viewOrder/{customerId}")
	public ResponseEntity viewOrder(@PathVariable int customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(orderServices.viewOrder(customerId));
	}
	
	//Function to ViewAllOrder(Admin Only)
	@GetMapping("/viewAllOrder/admin/{customerId}")
	public ResponseEntity viewAllOrder(@PathVariable int customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(orderServices.viewAllOrder(customerId));
	}
	
	//Function to CancelOrder
	@DeleteMapping("/cancelOrder/{orderId}/{customerId}")
	public boolean cancelOrder(@PathVariable int orderId, @PathVariable int customerId)
	{
		orderServices.cancelOrder(orderId, customerId);
//		return ResponseEntity.status(HttpStatus.OK).body("Order Cancelled");
		return true;
	}
	
	//Function to UpdateOrder
	@PutMapping("/updateOrder/{orderId}/{customerId}")
	public boolean updateOrder(@RequestBody Order order,@PathVariable int orderId, @PathVariable int customerId)
	{
		Order o = orderRepo.findById(orderId).get();
		orderServices.updateOrder(order, customerId);
		
//		return ResponseEntity.status(HttpStatus.OK).body(orderServices.updateOrder(order, customerId));
		return true;
	}
}
