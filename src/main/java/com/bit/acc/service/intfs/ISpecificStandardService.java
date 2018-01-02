package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.SpecificStandard;
import com.bit.common.db.IOperations;

public interface ISpecificStandardService extends IOperations<SpecificStandard> {
	
	public List<SpecificStandard> queryByAccStandard(Object accountingStandardID);
	
	public List<SpecificStandard> queryByAccStandard(String accountingStandardCode, int exeYear);
	
	public List<SpecificStandard> getTitlesByAccStandard(String accountingStandardCode, int exeYear);
	
}
