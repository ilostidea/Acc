package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.FinancialReportRepository;
import com.bit.acc.model.FinancialReport;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.FinancialReportService;

@Service("financialReportService")
public class FinancialReportServiceImpl extends AbstractService<FinancialReport, Long> implements FinancialReportService {
	
	@Autowired
	private FinancialReportRepository dao;

	@Override
	protected JpaRepository<FinancialReport, Long> getDao() {
		return this.dao;
	}

	@Override
	public List<FinancialReport> findByAccStandard(Long accountingStandardID) {
		return dao.findByAccountingStandardId(accountingStandardID);
	}

	@Override
	public List<FinancialReport> findByAccStandard(String accountingStandardCode, int exeYear) {
		return dao.findByAccountingStandardCodeAndAccountingStandardExeYear(accountingStandardCode, exeYear);
	}

}
