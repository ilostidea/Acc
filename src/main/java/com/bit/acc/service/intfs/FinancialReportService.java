package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.FinancialReport;
import com.bit.acc.service.baseservice.Service;

public interface FinancialReportService extends Service<FinancialReport, Long> {
	
	public List<FinancialReport> findByAccStandard(Long accountingStandardId);
	
	public List<FinancialReport> findByAccStandard(String accountingStandardCode, int exeYear);

}
