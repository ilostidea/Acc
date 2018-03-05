package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.Pump;
import com.bit.common.db.IOperations;

public interface IPumpDao extends IOperations<Pump> {
	
	public List<Pump> queryByAnswer(long questionID);
	
}
