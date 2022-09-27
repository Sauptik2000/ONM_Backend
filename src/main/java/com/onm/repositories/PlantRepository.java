package com.onm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onm.entity.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer>{
	@Query("select u from Plant u where u.plantName=?1")
	Plant findByplantName (String Plant);
}
