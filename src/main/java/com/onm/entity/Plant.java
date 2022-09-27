package com.onm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Plant {
	@Id
	@SequenceGenerator(
			   name ="plant_sequence",
	           sequenceName = "plant_sequence",
	           allocationSize = 1
	   )
	@GeneratedValue(
	           strategy = GenerationType.SEQUENCE,
	           generator = "plant_sequence"
	   )
	private int plantId;
	private String plantName;
	private String plantDesccription;
	private double plantCost;
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getPlantDesccription() {
		return plantDesccription;
	}
	public void setPlantDesccription(String plantDesccription) {
		this.plantDesccription = plantDesccription;
	}
	public double getPlantCost() {
		return plantCost;
	}
	public void setPlantCost(double plantCost) {
		this.plantCost = plantCost;
	}
	
	public Plant(int plantId, String plantName, String plantDesccription, double plantCost) {
		super();
		this.plantId = plantId;
		this.plantName = plantName;
		this.plantDesccription = plantDesccription;
		this.plantCost = plantCost;
	}
	
	public Plant() {
		super();
	}
	public Plant(String plantName, String plantDesccription, double plantCost) {
		super();
		this.plantName = plantName;
		this.plantDesccription = plantDesccription;
		this.plantCost = plantCost;
	}
	
	
}
