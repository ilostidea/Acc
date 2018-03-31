package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.AnswerCollectedRepository;
import com.bit.acc.model.AnswerCollected;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AnswerCollectedService;

@Service("answerCollectedService")
public class AnswerCollectedServiceImpl extends AbstractService<AnswerCollected, Long>
		implements AnswerCollectedService {
	
	@Autowired
	private AnswerCollectedRepository dao;
	
	protected JpaRepository<AnswerCollected, Long> getDao() {
		return dao;
	}

	@Override
	public List<AnswerCollected> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

}
