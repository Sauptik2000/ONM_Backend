package com.onm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onm.entity.Coupan;

@Repository
public interface CoupanRepository extends JpaRepository<Coupan, Integer>{
	
}
