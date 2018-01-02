package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.AccUsage;
import com.bit.common.db.IOperations;

public interface IAccUsageService extends IOperations<AccUsage> {
	
	public List<AccUsage> queryByAcc(Object coaID);
	
}
