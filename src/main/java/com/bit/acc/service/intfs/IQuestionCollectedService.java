package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.QuestionCollected;
import com.bit.common.db.IOperations;

public interface IQuestionCollectedService extends IOperations<QuestionCollected> {
	
	public List<QuestionCollected> queryByUser(long userID);

}
