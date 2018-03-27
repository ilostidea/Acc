package com.bit.acc.service.intfs;

import java.util.List;
import java.util.Map;

import com.bit.acc.model.AccountingStandard;
import com.bit.acc.service.baseservice.Service;

public interface AccountingStandardService extends Service<AccountingStandard, Long> {
	
	public List<Map<String, Object>> getNameExeyearsWithDistinctCode();
	
}
