package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.COARepository;
import com.bit.acc.model.COA;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.COAService;

@Service("coaService")
public class COAServiceImpl extends AbstractService<COA, Long> implements COAService {

	@Autowired
	private  COARepository dao;
	
	@Override
	protected JpaRepository<COA, Long> getDao() {
		return this.dao;
	}

	@Override
	public List<COA> findByAccountingStandardId(Object accountingStandardID) {
		return dao.findByAccountingStandardId(accountingStandardID);
	}

	@Override
	public List<COA> findByAccStandard(String accountingStandardCode, int exeYear) {
		return dao.findByAccStandard(accountingStandardCode, exeYear);
	}

	@Override
	public List<COA> findByAccStandardElement(String accountingStandardCode, int exeYear, String elementCode) {
		return dao.findByAccStandardElement(accountingStandardCode, exeYear, elementCode);
	}

}
