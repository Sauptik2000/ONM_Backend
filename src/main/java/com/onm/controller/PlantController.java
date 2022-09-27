package com.onm.controller;

import java.util.List;

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

import com.onm.entity.Plant;
import com.onm.services.PlantServices;

@RestController
@CrossOrigin
public class PlantController {
	@Autowired
	PlantServices plantServices;
	
	@PostMapping("/addPlant/{customerId}")
	public  ResponseEntity addPizza(@RequestBody Plant plant,@PathVariable int customerId) 
	{
		if(plantServices.addPlant(plant, customerId))
		{
			return ResponseEntity.status(HttpStatus.OK).body(plant);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Plant Already Exist");
	}
	
	@DeleteMapping("/deletePlant/{plantId}/{customerId}")
	public Boolean deletePlant(@PathVariable int plantId, @PathVariable int customerId)
	{
		plantServices.deletePlant(plantId, customerId);
		//return ResponseEntity.status(HttpStatus.OK).body("Plant Deleted");
		return true;
	}
	
	@PutMapping("/updatePlant/{customerId}")
	public ResponseEntity updatePlant(@RequestBody Plant plant, @PathVariable int customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(plantServices.updatePlant(plant, customerId));
	}
	
	@GetMapping("/viewPlant")
	public List viewPlant()
	{
		return plantServices.viewPlant();
	}
}
