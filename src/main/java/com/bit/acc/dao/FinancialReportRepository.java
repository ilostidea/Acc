package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.FinancialReport;

public interface FinancialReportRepository extends JpaRepository<FinancialReport, Long> {
	
	public List<FinancialReport> findByAccountingStandardId(Long accountingStandardId);
	
	public List<FinancialReport> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);

}
