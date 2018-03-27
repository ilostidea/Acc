package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.GeneralPrinciple;

public interface GeneralPrincipleRepository extends JpaRepository<GeneralPrinciple, Long> {
	
	public List<GeneralPrinciple> findByAccountingStandardId(Long accountingStandardID);
	
	public List<GeneralPrinciple> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);

}
