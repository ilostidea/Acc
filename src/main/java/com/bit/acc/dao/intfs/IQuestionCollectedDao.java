package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.QuestionCollected;
import com.bit.common.db.IOperations;

public interface IQuestionCollectedDao extends IOperations<QuestionCollected> {
	
	public List<QuestionCollected> queryByUser(long userID);
	
}
