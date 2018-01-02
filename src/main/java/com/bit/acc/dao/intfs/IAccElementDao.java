package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.AccElement;
import com.bit.common.db.IOperations;

public interface IAccElementDao extends IOperations<AccElement> {
	
	public List<AccElement> queryByAccStandard(Object accountingStandardID);
	
	public List<AccElement> queryByAccStandard(String accountingStandardName, int exeYear);
	
}
