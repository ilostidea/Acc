package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;
import com.bit.common.db.IOperations;

public interface IAnswerService extends IOperations<Answer> {

	public List<Answer> queryByQuestion(long questionID);
	
	public List<Answer> queryForAdmin(String userName, String answer, Boolean status, Boolean accused);
	
	public List<Answer> queryByUser(long userID);
	
	public List<Answer> queryByCollectedUser(long userID);
	
	public void approve(long id);
	
	public void disapprove(long id);
	
}
