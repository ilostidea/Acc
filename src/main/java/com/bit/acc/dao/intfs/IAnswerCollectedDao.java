package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.AnswerCollected;
import com.bit.common.db.IOperations;

public interface IAnswerCollectedDao extends IOperations<AnswerCollected> {
	
	public List<AnswerCollected> queryByUser( long userID );
	
}
