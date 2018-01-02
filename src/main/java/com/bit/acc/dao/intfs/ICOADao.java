package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.COA;
import com.bit.common.db.IOperations;

public interface ICOADao extends IOperations<COA> {
	
	public List<COA> queryByAccStandard(Object accountingStandardID);
	
	public List<COA> queryByAccStandard(String accountingStandardCode, int exeYear);
	
	public List<COA> queryByAccStandardElement(String accountingStandardCode, int exeYear, String elementCode) ;
	
}
