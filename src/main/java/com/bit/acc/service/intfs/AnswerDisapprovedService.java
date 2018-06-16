package com.bit.acc.service.intfs;

import com.bit.acc.model.AnswerDisapproved;
import com.bit.acc.service.baseservice.Service;

import java.util.List;

public interface AnswerDisapprovedService extends Service<AnswerDisapproved, Long> {

	public List<AnswerDisapproved> findByUser(Long userId);

	public List<AnswerDisapproved> findByUserAndAnswer(Long userId, Long answerId);
	
}
