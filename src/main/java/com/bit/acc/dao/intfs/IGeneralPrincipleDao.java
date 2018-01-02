package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.GeneralPrinciple;
import com.bit.common.db.IOperations;

public interface IGeneralPrincipleDao extends IOperations<GeneralPrinciple> {
	
	public List<GeneralPrinciple> queryByAccStandard(Object accountingStandardID);
	
	public List<GeneralPrinciple> queryByAccStandard(String accountingStandardCode, int exeYear);
	
}
