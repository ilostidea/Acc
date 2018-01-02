package com.bit.acc.service.impl;

import java.util.List;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.ICOADao;
import com.bit.acc.model.COA;
import com.bit.acc.service.intfs.ICOAService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

/**
 * Home object for domain model class Coa.
 * @see com.bit.acc.dao.impl.COA
 * @author Hibernate Tools
 */

@Service("coaService")
public class COAService extends AbstractService<COA> implements ICOAService{
	
	@Resource(name = "coaDao")
	private ICOADao dao;

	@Override
	protected IOperations<COA> getDao() {
		return this.dao;
	}
	
	public List<COA> queryByAccStandard(Object accountingStandardID) {
		List<COA> listCOA = dao.queryByAccStandard(accountingStandardID);
		return listCOA;
	}
	
	public List<COA> queryByAccStandard(String accountingStandardCode, int exeYear) {
		List<COA> listCOA = dao.queryByAccStandard(accountingStandardCode, exeYear);
		return listCOA;
	}

	public List<COA> queryByAccStandardElement(String accountingStandardCode, int exeYear, String elementCode) {
		List<COA> listCOA = dao.queryByAccStandardElement(accountingStandardCode, exeYear, elementCode);
		return listCOA;
	}

}
