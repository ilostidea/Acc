package com.bit.acc.service.impl;

import java.util.List;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.ISpecificStandardDao;
import com.bit.acc.model.GeneralPrinciple;
import com.bit.acc.model.SpecificStandard;
import com.bit.acc.service.intfs.ISpecificStandardService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

/**
 * Home object for domain model class Specificstandard.
 * @see com.bit.acc.dao.impl.SpecificStandard
 * @author Hibernate Tools
 */
@Service("specificStandardService")
public class SpecificStandardService extends AbstractService<SpecificStandard> implements ISpecificStandardService{

	@Resource(name = "specificStandardDao")
	private ISpecificStandardDao dao;

	@Override
	protected IOperations<SpecificStandard> getDao() {
		return this.dao;
	}
	
	public List<SpecificStandard> queryByAccStandard(Object accountingStandardID) {
		List<SpecificStandard> listSpecificStandard = dao.queryByAccStandard(accountingStandardID);
		return listSpecificStandard;
	}
	
	public List<SpecificStandard> queryByAccStandard(String accountingStandardCode, int exeYear) {
		List<SpecificStandard> listSpecificStandard = dao.queryByAccStandard(accountingStandardCode, exeYear);
		/*SpecificStandard specificStandard = null;
		if(listSpecificStandard != null && listSpecificStandard.size() > 0)
			specificStandard = listSpecificStandard.get(0);
		return specificStandard;*/
		return listSpecificStandard;
	}
	
	public List<SpecificStandard> getTitlesByAccStandard(String accountingStandardCode, int exeYear) {
		return dao.getTitlesByAccStandard(accountingStandardCode, exeYear);
	}

}
