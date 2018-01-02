package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.FinancialReport;
import com.bit.common.db.IOperations;

public interface IFinancialReportDao extends IOperations<FinancialReport> {
	
	public List<FinancialReport> queryByAccStandard(Object accountingStandardID);
	
	public List<FinancialReport> queryByAccStandard(String accountingStandardCode, int exeYear);
	
}
