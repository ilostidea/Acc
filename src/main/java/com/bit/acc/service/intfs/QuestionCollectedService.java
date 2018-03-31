package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.QuestionCollected;
import com.bit.acc.service.baseservice.Service;

public interface QuestionCollectedService extends Service<QuestionCollected, Long> {

	public List<QuestionCollected> findByUser(Long userId);
	
}
