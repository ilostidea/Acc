package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.Pump;
import com.bit.acc.service.baseservice.Service;

public interface PumpService extends Service<Pump, Long> {
	
	public List<Pump> findByAnswer(Long answerId);

}
