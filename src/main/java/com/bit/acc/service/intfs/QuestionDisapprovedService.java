package com.bit.acc.service.intfs;

import com.bit.acc.model.QuestionDisapproved;
import com.bit.acc.service.baseservice.Service;

import java.util.List;

public interface QuestionDisapprovedService extends Service<QuestionDisapproved, Long> {

	public List<QuestionDisapproved> findByUser(Long userId);

	public List<QuestionDisapproved> findByUserAndQuestion(Long userId, Long questionId);
	
}
