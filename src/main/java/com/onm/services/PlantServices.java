package com.onm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onm.entity.Customer;
import com.onm.entity.Plant;
import com.onm.expections.NotAdminException;
import com.onm.expections.ResourceNotFoundException;
import com.onm.repositories.CustomerRepository;
import com.onm.repositories.PlantRepository;

@Service
public class PlantServices {
	public String msgAD = "Access Denied";
	public String msgUNF = "User not found";
	
	@Autowired
	PlantRepository plantRepo;
	@Autowired
	CustomerRepository customerRepo;
	
	public boolean addPlant(Plant plant, int customerId) {
		Plant pl = plantRepo.findByplantName(plant.getPlantName());
		Customer c =  customerRepo.findById(customerId).get();
		
		if(c == null) {
			throw new ResourceNotFoundException(msgUNF);
		}else if(c.getCustomerType().equals("user")){
			throw new NotAdminException(msgAD);
		}
		if(pl == null) {
			plantRepo.save(plant);
			return true;
		}else {
			return false;
		}
	}
	
	public void deletePlant(int plantId, int customerId) {
		Customer c = customerRepo.findById(customerId).get();
		if(c == null) {
			throw new ResourceNotFoundException(msgUNF);
		}else if(c.getCustomerType().equals("user")) {
			throw new NotAdminException(msgAD);
		}else if(plantRepo.findById(plantId).isPresent()) {
			plantRepo.deleteById(plantId);
		}
	}
	
	public Plant updatePlant(Plant plant, int customerId) {
		Customer c = customerRepo.findById(customerId).get();
		Plant pl = plantRepo.findById(plant.getPlantId()).get();
		if(c == null) {
			throw new ResourceNotFoundException(msgUNF);
		}else if(c.getCustomerType().equals("user")) {
			throw new NotAdminException(msgAD);
		}else if(pl != null) {
			pl.setPlantCost(plant.getPlantCost());
			pl.setPlantDesccription(plant.getPlantDesccription());
			pl.setPlantName(plant.getPlantName());
			plantRepo.save(pl);
			return pl;
		}else {
			throw new ResourceNotFoundException("Plant not found ");
		}
	}
	
	public List viewPlant() {
		return plantRepo.findAll();
	}
}
