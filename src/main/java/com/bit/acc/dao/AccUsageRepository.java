package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.AccUsage;

public interface AccUsageRepository extends JpaRepository<AccUsage, Long> {
	
	public List<AccUsage> findByCoaId(Long coaId);

}
