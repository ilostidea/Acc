package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.Pump;

public interface PumpRepository extends JpaRepository<Pump, Long> {

	public List<Pump> findByAnswerId(Long answerId);
	
}
