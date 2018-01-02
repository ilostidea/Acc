package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.AccountingStandard;
import com.bit.common.db.IOperations;

public interface IAccountingStandardDao extends IOperations<AccountingStandard> {
	
	public List<String[]> getDistinctNameCode();
	
}
