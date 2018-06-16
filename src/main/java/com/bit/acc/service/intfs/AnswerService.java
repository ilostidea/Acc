package com.bit.acc.service.intfs;

import com.bit.acc.model.Answer;
import com.bit.acc.model.AnswerApproved;
import com.bit.acc.model.AnswerDisapproved;
import com.bit.acc.service.baseservice.Service;

import java.util.List;

public interface AnswerService extends Service<Answer, Long> {

	public List<Answer> findByQuestion(Long questionId, Long userId);
	
	public List<Answer> findForAdmin(String userName, String answer, Boolean status, Boolean accused);
	
	public List<Answer> findByUser(Long userId);
	
	public List<Answer> findByCollectedUser(Long userId);
	
	public Answer getAnswerQuestionPumps(Long id);
	
	public void switchStatus(Long id, Boolean status);
	
	public void approve(Long id, Long userId, AnswerDisapproved answerDisapproved);
	
	public void disapprove(Long id, Long userId, AnswerApproved answerApproved);

}
