package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.GeneralPrincipleRepository;
import com.bit.acc.model.GeneralPrinciple;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.GeneralPrincipleService;

@Service("generalPrincipleService")
public class GeneralPrincipleServiceImpl extends AbstractService<GeneralPrinciple, Long> implements GeneralPrincipleService {
	
	@Autowired
	private GeneralPrincipleRepository dao;

	@Override
	protected JpaRepository<GeneralPrinciple, Long> getDao() {
		return this.dao;
	}

	public GeneralPrinciple findByAccStandard(Long accountingStandardID) {
		List<GeneralPrinciple> listGeneralPrinciple= dao.findByAccountingStandardId(accountingStandardID);
		GeneralPrinciple generalPrinciple = null;
		if(listGeneralPrinciple != null && listGeneralPrinciple.size() > 0)
			generalPrinciple = listGeneralPrinciple.get(0);
		return generalPrinciple;
	}
	
	public GeneralPrinciple findByAccStandard(String accountingStandardCode, int exeYear) {
		List<GeneralPrinciple> listGeneralPrinciple= dao.findByAccountingStandardCodeAndAccountingStandardExeYear(accountingStandardCode, exeYear);
		GeneralPrinciple generalPrinciple = null;
		if(listGeneralPrinciple != null && listGeneralPrinciple.size() > 0)
			generalPrinciple = listGeneralPrinciple.get(0);
		return generalPrinciple;
	}
	
}
