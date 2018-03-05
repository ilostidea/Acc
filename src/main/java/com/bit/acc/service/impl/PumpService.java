package com.bit.acc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IPumpDao;
import com.bit.acc.model.Pump;
import com.bit.acc.service.intfs.IPumpService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("pumpService")
public class PumpService extends AbstractService<Pump> implements IPumpService{
	
	@Resource(name = "pumpDao")
	private IPumpDao dao;

	@Override
	protected IOperations<Pump> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(Pump transientInstance) {
		super.persist(transientInstance);
	}
	
	public List<Pump> queryByAnswer(long questionID) {
		return dao.queryByAnswer(questionID);
	}
	
}
