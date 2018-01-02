package com.bit.acc.service.impl;

import java.util.List;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.IAccUsageDao;
import com.bit.acc.model.AccUsage;
import com.bit.acc.service.intfs.IAccUsageService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("accUsageService")
public class AccUsageService extends AbstractService<AccUsage> implements IAccUsageService{
	
	@Resource(name = "accUsageDao")
	private IAccUsageDao dao;

	@Override
	protected IOperations<AccUsage> getDao() {
		return this.dao;
	}
	
	public List<AccUsage> queryByAcc(Object coaID) {
		List<AccUsage> listAccUsage = dao.queryByAcc( coaID );
		return listAccUsage;
	}

}
