package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.Answer;
import com.bit.acc.service.baseservice.Service;

public interface AnswerService extends Service<Answer, Long> {

	public List<Answer> findByQuestion(Long questionId);
	
	public List<Answer> findForAdmin(String userName, String answer, Boolean status, Boolean accused);
	
	public List<Answer> findByUser(Long userId);
	
	public List<Answer> findByCollectedUser(Long userId);
	
	public void approve(Long id);
	
	public void disapprove(Long id);

}
