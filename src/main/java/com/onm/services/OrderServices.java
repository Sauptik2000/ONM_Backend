package com.onm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onm.entity.Coupan;
import com.onm.entity.Customer;
import com.onm.entity.Order;
import com.onm.entity.Plant;
import com.onm.expections.ResourceNotFoundException;
import com.onm.repositories.CoupanRepository;
import com.onm.repositories.CustomerRepository;
import com.onm.repositories.OrderRepository;
import com.onm.repositories.PlantRepository;

@Service
public class OrderServices {
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	PlantRepository plantRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	CoupanRepository coupanRepo;
	
	//Function of BookOrder
	public boolean bookOrder(Order order, int customerId, int plantId)
	{
		if(!customerRepo.findById(customerId).isPresent())
		{
			throw new ResourceNotFoundException("User not found "); 
		}
		if(!plantRepo.findById(plantId).isPresent())
		{
			throw new ResourceNotFoundException("Plant not found "); 
		}
		order.setCustomerId(customerId);
		order.setPlantName(plantRepo.findById(plantId).get().getPlantName());
		Plant p = plantRepo.findById(plantId).get();
		Coupan c = coupanRepo.findById(order.getCoupanId()).get();
		
		if(c==null) {
			throw new ResourceNotFoundException("Coupon not found");
		}
		
		
		if(c.getCouponValue()<(order.getOrderQuantity()*p.getPlantCost())) {
			throw new ResourceNotFoundException("Coupon undervalued");
		}
		order.setOrderAmount(order.getOrderQuantity()*p.getPlantCost());
		
		orderRepo.save(order);
		return true;
	}
	
	//Function Of ViewOrder
	public List viewOrder(int customerId) 
	{
		return orderRepo.findByCustomerId(customerId);
	}
	
	//Function of ViewAllOrder(Admin only)
	public List viewAllOrder(int customerId)
	{
		Customer cust = customerRepo.findById(customerId).get();
		if(cust == null)
		{
			throw new ResourceNotFoundException("User not found "); 
		}
		else if (cust.getCustomerType().equals("user")) 
		{
			throw new ResourceNotFoundException("Access Denied");
		}
		else
		{
			return orderRepo.findAll();
		}
	}
	
	//Function of CancelOrder
	public void cancelOrder(int orderId, int customerId)
	{
		Customer cust = customerRepo.findById(customerId).get();
		if(cust == null) {
			throw new ResourceNotFoundException("Customer Not Found");
		}
		else if (orderRepo.findById(orderId).isPresent()) {
			orderRepo.deleteById(orderId);
		}
	}
		
	//Function of UpdateOrder
	public Order updateOrder(Order order, int customerId) 
	{
		Customer cust = customerRepo.findById(customerId).get();
		Order o = orderRepo.findById(order.getOrderId()).get();
		
		Coupan c = coupanRepo.findById(order.getCoupanId()).get();
		Plant p = plantRepo.findByplantName(order.getPlantName());
		
		if(cust == null) {
			throw new ResourceNotFoundException("Customer Not Found");
		}
		else if(o != null) {
			o.setOrderQuantity(order.getOrderQuantity());
			Double am = order.getOrderQuantity()*p.getPlantCost();
			o.setOrderAmount(am);
			if(c.getCouponValue()<o.getOrderAmount()) {
				throw new ResourceNotFoundException("Coupan Undervalued, Can't Modify");
			}
			orderRepo.save(o);
			return o;
		}
		else {
			throw new ResourceNotFoundException("Order Not Found");
		}
	}
}
