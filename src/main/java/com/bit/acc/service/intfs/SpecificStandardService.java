package com.bit.acc.service.intfs;

import java.util.List;
import java.util.Map;

import com.bit.acc.model.SpecificStandard;
import com.bit.acc.service.baseservice.Service;

public interface SpecificStandardService extends Service<SpecificStandard, Long> {

    public List<SpecificStandard> findByAccStandard(Long accountingStandardID);
	
	public List<SpecificStandard> findByAccStandard(String accountingStandardCode, int exeYear);

	public Map<String, Object> getTitlesByAccStandard(String accountingStandardCode, int exeYear) ;
	
}
