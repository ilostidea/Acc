package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.AccElementRepository;
import com.bit.acc.model.AccElement;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AccElementService;

@Service("accElementService")
public class AccElementServiceImpl extends AbstractService<AccElement, Long> implements AccElementService {
	
	@Autowired
	private AccElementRepository dao;

	@Override
	protected JpaRepository<AccElement, Long> getDao() {
		return this.dao;
	}

	@Override
	public List<AccElement> findByAccStandard(Long accountingStandardId) {
		return dao.findByAccountingStandardId(accountingStandardId);
	}

	@Override
	public List<AccElement> findByAccStandard(String accountingStandardCode, int exeYear) {
		return dao.findByAccountingStandardCodeAndAccountingStandardExeYear(accountingStandardCode, exeYear);
	}

}
