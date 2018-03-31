package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.AccUsage;
import com.bit.acc.service.baseservice.Service;

public interface AccUsageService extends Service<AccUsage, Long> {
	
	public List<AccUsage> queryByAcc(Long coaId);

}
