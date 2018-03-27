package com.bit.acc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.AccountingStandardRepository;
import com.bit.acc.model.AccountingStandard;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AccountingStandardService;

@Service("accStandardService")
public class AccountingStandardServiceImpl extends AbstractService<AccountingStandard, Long> implements AccountingStandardService {
	
	@Autowired
	private AccountingStandardRepository dao;
	
	protected JpaRepository <AccountingStandard, Long> getDao() {
		return this.dao;
	}

	@Override
	public List<Map<String, Object>> getNameExeyearsWithDistinctCode() {
		//String jpql = "select t from " + AccountingStandard.class.getName() + " as t where t.status = true";
		AccountingStandard example = new AccountingStandard();
		example.setStatus(true);
		List< AccountingStandard > resultList = findAll( example  );

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
