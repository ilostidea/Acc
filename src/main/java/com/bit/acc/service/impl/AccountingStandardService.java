package com.bit.acc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.IAccountingStandardDao;
import com.bit.acc.model.AccountingStandard;
import com.bit.acc.service.intfs.IAccountingStandardService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;


@Service("accountingStandardService")
public class AccountingStandardService extends AbstractService<AccountingStandard> implements IAccountingStandardService{
	
	@Resource(name = "accountingStandardDao")
	private IAccountingStandardDao dao;
	
	@Override
	protected IOperations<AccountingStandard> getDao() {
		return this.dao;
	}
	
	@Override
	public List<Map<String, Object>> getNameExeyearsWithDistinctCode() {
		String jpql = "select t from " + AccountingStandard.class.getName() + " as t where t.status = true";
		List< AccountingStandard > resultList = findAll( jpql );

		Map<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for( AccountingStandard result : resultList ) {
			Map<String, Object> resultMap;
			List<Integer> exeYearsList;
			String code = result.getCode();
			if( tempMap.containsKey( code ) ) {
				resultMap = tempMap.get( code );
				exeYearsList = ( List<Integer> ) resultMap.get("exeYears");
			} else {
				resultMap = new HashMap<String, Object>();
				exeYearsList = new ArrayList<Integer>();
				resultMap.put("exeYears", exeYearsList);
				tempMap.put(code, resultMap);
				returnList.add( resultMap );
			}
			String name = result.getName();
			Integer exeYear = result.getExeYear();
			resultMap.put("name", name);
			resultMap.put("code", code);
			exeYearsList.add( exeYear );
			resultMap.put("exeYears", exeYearsList);
		}
		
		return returnList;
	}

}
