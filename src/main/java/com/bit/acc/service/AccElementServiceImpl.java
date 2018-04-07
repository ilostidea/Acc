package com.bit.acc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Map<String, ?>> findCoaUsagesByAccountingStandard(String accountingStandardCode, int exeYear) {
		List<Map<String, ?>> coaUsages = dao.findCoaUsagesByAccountingStandardCodeAndExeYear(accountingStandardCode, exeYear);
		List<Map<String,?>> resultList = new ArrayList<>();
		
		Map<Long, Map<String, Object>> elementIdMap = new HashMap<>();
		Map<Long, Map<String, Object>> coaIdMap = new HashMap<>();

		for( Map<String, ?> coaUsage : coaUsages ) {
			Long accElementId = (Long) coaUsage.get("accElementId");
			Map<String, Object> elementMap = (Map<String, Object>) elementIdMap.get(accElementId);
			if( elementMap == null ) {
				elementMap = new HashMap<>();
				resultList.add(elementMap);
			}
			putElement( elementMap, coaUsage, elementIdMap, coaIdMap );
		}
		return resultList;
	}
	
	private void putElement(Map<String, Object> elementMap, Map<String, ?> coaUsage, Map<Long, Map<String, Object>> elementIdMap, Map<Long,  Map<String, Object>> coaIdMap ) {
		if( !elementIdMap.containsKey(coaUsage.get("accElementId")) ) {
			elementIdMap.put( (Long)coaUsage.get("accElementId"), elementMap);
			elementMap.put( "id", coaUsage.get("accElementId") );
			elementMap.put( "accountingStandard", coaUsage.get("accountingStandard") );
			elementMap.put( "accElementCode", coaUsage.get("accElementCode") );
			elementMap.put( "accElementName", coaUsage.get("accElementName") );
		}

		List<Map<String, Object>> accList = (List<Map<String, Object>>) elementMap.get("subjects");
		if( accList == null ) {
			accList = new ArrayList<>();
			elementMap.put("subjects", accList);
		}

		Long accId = (Long) coaUsage.get("accId");
		Map<String, Object> accMap = coaIdMap.get(accId);
		if(accMap == null) {
			accMap = new HashMap<>();
			coaIdMap.put(accId, accMap);
			accList.add( accMap );
			accMap.put("id", accId);
			accMap.put("code", coaUsage.get("accCode"));
			accMap.put("name", coaUsage.get("accName"));
		}

		List<Map<String, Object>> usageList = (List<Map<String, Object>>) accMap.get("usages");
		if( usageList == null ) {
			usageList = new ArrayList<>();
			accMap.put("usages", usageList);
		}
		Map<String, Object> usageMap = new HashMap<>();
		usageMap.put("id", coaUsage.get("usageId"));
		usageMap.put("content", coaUsage.get("usages"));
		usageList.add(usageMap);
	}

}
