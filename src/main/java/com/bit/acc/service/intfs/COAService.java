package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.COA;
import com.bit.acc.service.baseservice.Service;

public interface COAService extends Service<COA, Long> {

    public List<COA> findByAccountingStandardId(Object accountingStandardID);

	public List<COA> findByAccStandard( String accountingStandardCode, int exeYear );

	public List<COA> findByAccStandardElement( String accountingStandardCode, int exeYear, String elementCode ) ;
	
}
