package com.bit.acc.service.intfs;

import com.bit.acc.model.QuestionApproved;
import com.bit.acc.service.baseservice.Service;

import java.util.List;

public interface QuestionApprovedService extends Service<QuestionApproved, Long> {

	public List<QuestionApproved> findByUser(Long userId);

	public List<QuestionApproved> findByUserAndQuestion(Long userId, Long questionId);
	
}
