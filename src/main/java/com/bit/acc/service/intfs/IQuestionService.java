package com.bit.acc.service.intfs;

import java.util.List;
import java.util.Map;

import com.bit.acc.model.Question;
import com.bit.common.db.IOperations;

public interface IQuestionService extends IOperations<Question> {

	public List<Question> queryRecent( );
	
	public List<Question> queryForAdmin(String userName, String question, Boolean status, Boolean accused);

	public List<Question> queryByUser(long userID);

	public List<Question> queryByAnsweredUser(long userID);
	
	public List<Question> queryByCollectedUser(long userID);
	
	public Question getQuesstionAndAnswersById(long id);
	
	public Question getQuesstionAndAnswersPumpCountById(long id) ;
	
	public Map getQuestionProfileById(long userID);
	
}
