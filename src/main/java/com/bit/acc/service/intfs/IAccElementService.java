package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.AccElement;
import com.bit.common.db.IOperations;

public interface IAccElementService extends IOperations<AccElement> {
	
	public List<AccElement> queryByAccStandard(Object accountingStandardID);
	
	public List<AccElement> queryByAccStandard(String accountingStandardCode, int exeYear);
	
}
