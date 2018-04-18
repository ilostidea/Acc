package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bit.acc.model.Pump;

public interface PumpRepository extends JpaRepository<Pump, Long> {

	public List<Pump> findByAnswerId(Long answerId);
	
	@Modifying
	@Query("update Pump set status = ?2 where id = ?1")
	public void switchStatus(Long id, Boolean status);
	
}
