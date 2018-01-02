package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.AccUsage;
import com.bit.common.db.IOperations;

public interface IAccUsageDao extends IOperations<AccUsage> {
	
	public List<AccUsage> queryByAcc(Object coaID);
	
}
