package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.AnswerCollected;
import com.bit.common.db.IOperations;

public interface IAnswerCollectedService extends IOperations<AnswerCollected> {
	
	public List<AnswerCollected> queryByUser( long userID );

}
