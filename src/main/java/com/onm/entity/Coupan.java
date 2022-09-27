package com.onm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "coupans")
public class Coupan {
	@Id
	@SequenceGenerator(
	           name ="coupon_sequence",
	           sequenceName = "coupon_sequence",
	           allocationSize = 1
	   )
	@GeneratedValue(
	           strategy = GenerationType.SEQUENCE,
	           generator = "coupon_sequence"
	   )
	private int coupanId;
	private String coupanName;

	private String coupanType;
	private String coupanDescription;
	private Double couponValue;
	
	public Coupan() {
	}

	public Coupan(String coupanName, String coupanType, String coupanDescription, Double couponValue) {
		this.coupanName = coupanName;
		this.coupanType = coupanType;
		this.coupanDescription = coupanDescription;
		this.couponValue = couponValue;
	}

	public Coupan(String coupanName, String coupanType, Double couponValue) {
		this.coupanName = coupanName;
		this.coupanType = coupanType;
		this.couponValue = couponValue;
	}


	

	public Coupan(int coupanId, String coupanName, String coupanType, String coupanDescription, Double couponValue) {
		this.coupanId = coupanId;
		this.coupanName = coupanName;
		this.coupanType = coupanType;
		this.coupanDescription = coupanDescription;
		this.couponValue = couponValue;
	}

	public Double getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(Double couponValue) {
		this.couponValue = couponValue;
	}

	public int getCoupanId() {
		return coupanId;
	}

	public void setCoupanId(int coupanId) {
		this.coupanId = coupanId;
	}

	public String getCoupanName() {
		return coupanName;
	}

	public void setCoupanName(String coupanName) {
		this.coupanName = coupanName;
	}

	public String getCoupanType() {
		return coupanType;
	}

	public void setCoupanType(String coupanType) {
		this.coupanType = coupanType;
	}

	public String getCoupanDescription() {
		return coupanDescription;
	}

	public void setCoupanDescription(String coupanDescription) {
		this.coupanDescription = coupanDescription;
	}
}