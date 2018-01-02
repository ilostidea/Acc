package com.bit.acc.service.impl;

import java.util.List;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.IAccElementDao;
import com.bit.acc.model.AccElement;
import com.bit.acc.service.intfs.IAccElementService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

/**
 * Home object for domain model class AccElement.
 * @see com.bit.acc.dao.impl.AccElementDao
 * @author Hibernate Tools
 */

@Service("accElementService")
public class AccElementService extends AbstractService<AccElement> implements IAccElementService{
	
	@Resource(name = "accElementDao")
	private IAccElementDao dao;

	@Override
	protected IOperations<AccElement> getDao() {
		return this.dao;
	}
	
	public List<AccElement> queryByAccStandard(Object accountingStandardID) {
		List<AccElement> listAccElement = dao.queryByAccStandard(accountingStandardID);
		return listAccElement;
	}
	
	public List<AccElement> queryByAccStandard(String accountingStandardCode, int exeYear) {
		List<AccElement> listAccElement = dao.queryByAccStandard(accountingStandardCode, exeYear);
		return listAccElement;
	}

}
