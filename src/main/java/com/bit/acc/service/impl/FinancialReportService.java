package com.bit.acc.service.impl;

import java.util.List;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.IFinancialReportDao;
import com.bit.acc.model.FinancialReport;
import com.bit.acc.service.intfs.IFinancialReportService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

/**
 * Home object for domain model class FinancialReport.
 * @see com.bit.acc.dao.impl.FinancialReport
 * @author Hibernate Tools
 */
@Service("financialReportService")
public class FinancialReportService extends AbstractService<FinancialReport> implements IFinancialReportService{
	
	@Resource(name = "financialReportDao")
	private IFinancialReportDao dao;

	@Override
	protected IOperations<FinancialReport> getDao() {
		return this.dao;
	}
	
	public List<FinancialReport> queryByAccStandard(Object accountingStandardID) {
		List<FinancialReport> listFinancialReport= dao.queryByAccStandard(accountingStandardID);
		return listFinancialReport;
	}
	
	public List<FinancialReport> queryByAccStandard(String accountingStandardCode, int exeYear) {
		List<FinancialReport> listFinancialReport= dao.queryByAccStandard(accountingStandardCode, exeYear);
		return listFinancialReport;
	}

}
