package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.AccUsageRepository;
import com.bit.acc.model.AccUsage;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AccUsageService;

@Service("accUsageService")
public class AccUsageServiceImpl extends AbstractService<AccUsage, Long> implements AccUsageService {

	@Autowired
	private AccUsageRepository dao;
	
	protected JpaRepository <AccUsage, Long> getDao() {
		return this.dao;
	}
	
	@Override
	public List<AccUsage> queryByAcc(Long coaId) {
		return dao.findByCoaId(coaId);
	}

}
