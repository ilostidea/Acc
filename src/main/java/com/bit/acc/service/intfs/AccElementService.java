package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.AccElement;
import com.bit.acc.service.baseservice.Service;

public interface AccElementService extends Service<AccElement, Long> {
	
    public List<AccElement> findByAccStandard(Long accountingStandardID) ;
	
	public List<AccElement> findByAccStandard(String accountingStandardCode, int exeYear) ;
	
}
