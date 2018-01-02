package com.bit.acc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.ISysLogDao;
import com.bit.acc.model.SysLog;
import com.bit.acc.service.intfs.ISysLogService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("sysLogService")
public class SysLogService extends AbstractService<SysLog> implements ISysLogService{
	
	@Resource(name = "sysLogDao")
	private ISysLogDao dao;
	
	/*@Resource(name = "employeeService")
	private IEmployeeService employeeService;*/

	@Override
	protected IOperations<SysLog> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(SysLog transientInstance) {
		
		super.persist(transientInstance);
	}
	

}
