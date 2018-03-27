package com.bit.acc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.SysLogRepository;
import com.bit.acc.model.SysLog;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.SysLogService;

@Service("sysLogService")
public class SysLogServiceImpl extends AbstractService<SysLog, Long> implements SysLogService {
	
	@Autowired
	private SysLogRepository dao;

	protected JpaRepository<SysLog, Long> getDao() {
		return dao;
	}

}
