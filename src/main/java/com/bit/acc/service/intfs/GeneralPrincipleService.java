package com.bit.acc.service.intfs;

import com.bit.acc.model.GeneralPrinciple;
import com.bit.acc.service.baseservice.Service;

public interface GeneralPrincipleService extends Service<GeneralPrinciple, Long> {
	
	public GeneralPrinciple findByAccStandard(Long accountingStandardId) ;
	
	public GeneralPrinciple findByAccStandard(String accountingStandardCode, int exeYear);

}
