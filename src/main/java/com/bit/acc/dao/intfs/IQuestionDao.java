package com.bit.acc.dao.intfs;

import java.util.List;
import java.util.Map;

import com.bit.acc.model.Question;
import com.bit.common.db.IOperations;

public interface IQuestionDao extends IOperations<Question> {
	
	public List<Question> queryByUser(long userID);

	public List<Question> queryByAnsweredUser(long userID);
	
	public List<Question> queryByCollectedUser(long userID);
	
	public Question getQuesstionAndAnswersById(long id);
	
	public Map getQuestionProfileById(long userID);
	
}
