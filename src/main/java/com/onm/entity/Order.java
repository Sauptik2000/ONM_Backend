package com.onm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@SequenceGenerator(
			   name ="order_sequence",
	           sequenceName = "order_sequence",
	           allocationSize = 1
	   )
	@GeneratedValue(
	           strategy = GenerationType.SEQUENCE,
	           generator = "order_sequence"
	   )
	private int orderId;
	private int orderQuantity;
	private double orderAmount;
	private int customerId;
	private String plantName;
	private int coupanId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public Order(int orderId, int orderQuantity, double orderAmount, int customerId, String plantName, int coupanId) {
		super();
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.orderAmount = orderAmount;
		this.customerId = customerId;
		this.plantName = plantName;
		this.coupanId = coupanId;
	}
	public int getCoupanId() {
		return coupanId;
	}
	public void setCoupanId(int coupanId) {
		this.coupanId = coupanId;
	}
	public Order() {
		super();
	}
	public Order(int orderQuantity) {
		super();
		this.orderQuantity = orderQuantity;
	}
	
	
	

}
