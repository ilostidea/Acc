package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.AccElement;

public interface AccElementRepository extends JpaRepository<AccElement, Long> {
	
	public List<AccElement> findByAccountingStandardId(Long accountingStandardId) ;
	
	public List<AccElement> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);

}
