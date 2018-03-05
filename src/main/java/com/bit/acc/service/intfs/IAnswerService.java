package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.Answer;
import com.bit.common.db.IOperations;

public interface IAnswerService extends IOperations<Answer> {

	public List<Answer> queryByQuestion(long questionID);
	
	public void approve(long id);
	
	public void disapprove(long id);
	
}
