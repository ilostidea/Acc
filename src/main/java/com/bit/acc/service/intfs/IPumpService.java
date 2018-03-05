package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.Pump;
import com.bit.common.db.IOperations;

public interface IPumpService extends IOperations<Pump> {
	
	public List<Pump> queryByAnswer(long questionID);

}
