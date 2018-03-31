package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.AnswerCollected;
import com.bit.acc.service.baseservice.Service;

public interface AnswerCollectedService extends Service<AnswerCollected, Long> {

	public List<AnswerCollected> findByUser( Long userId );
	
}
