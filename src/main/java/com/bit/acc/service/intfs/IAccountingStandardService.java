package com.bit.acc.service.intfs;

import java.util.List;
import java.util.Map;

import com.bit.acc.model.AccountingStandard;
import com.bit.common.db.IOperations;

public interface IAccountingStandardService extends IOperations<AccountingStandard> {
	
	public List<Map<String, Object>> getNameExeyearsWithDistinctCode();
	
}
