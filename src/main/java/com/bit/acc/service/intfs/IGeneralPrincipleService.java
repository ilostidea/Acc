package com.bit.acc.service.intfs;

import com.bit.acc.model.GeneralPrinciple;
import com.bit.common.db.IOperations;

public interface IGeneralPrincipleService extends IOperations<GeneralPrinciple> {
	
	public GeneralPrinciple queryByAccStandard(Object accountingStandardID);
	
	public GeneralPrinciple queryByAccStandard(String accountingStandardCode, int exeYear);
	
}
