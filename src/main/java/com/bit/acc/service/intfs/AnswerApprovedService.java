package com.bit.acc.service.intfs;

import com.bit.acc.model.AnswerApproved;
import com.bit.acc.service.baseservice.Service;

import java.util.List;

public interface AnswerApprovedService extends Service<AnswerApproved, Long> {

	public List<AnswerApproved> findByUser(Long userId);

	public List<AnswerApproved> findByUserAndAnswer(Long userId, Long answerId);
	
}
