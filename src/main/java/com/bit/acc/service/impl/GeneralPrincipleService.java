package com.bit.acc.service.impl;

import java.util.List;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.IGeneralPrincipleDao;
import com.bit.acc.model.GeneralPrinciple;
import com.bit.acc.service.intfs.IGeneralPrincipleService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

/**
 * Home object for domain model class Generalprinciple.
 * @see com.bit.acc.dao.impl.GeneralPrinciple
 * @author Hibernate Tools
 */
@Service("generalPrincipleService")
public class GeneralPrincipleService extends AbstractService<GeneralPrinciple> implements IGeneralPrincipleService{
	
	@Resource(name = "generalPrincipleDao")
	private IGeneralPrincipleDao dao;

	@Override
	protected IOperations<GeneralPrinciple> getDao() {
		return this.dao;
	}
	
	public GeneralPrinciple queryByAccStandard(Object accountingStandardID) {
		List<GeneralPrinciple> listGeneralPrinciple= dao.queryByAccStandard(accountingStandardID);
		GeneralPrinciple generalPrinciple = null;
		if(listGeneralPrinciple != null && listGeneralPrinciple.size() > 0)
			generalPrinciple = listGeneralPrinciple.get(0);
		return generalPrinciple;
	}
	
	public GeneralPrinciple queryByAccStandard(String accountingStandardCode, int exeYear) {
		List<GeneralPrinciple> listGeneralPrinciple= dao.queryByAccStandard(accountingStandardCode, exeYear);
		GeneralPrinciple generalPrinciple = null;
		if(listGeneralPrinciple != null && listGeneralPrinciple.size() > 0)
			generalPrinciple = listGeneralPrinciple.get(0);
		return generalPrinciple;
	}

}
