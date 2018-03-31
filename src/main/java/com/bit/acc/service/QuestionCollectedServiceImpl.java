package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.QuestionCollectedRepository;
import com.bit.acc.model.QuestionCollected;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.QuestionCollectedService;

@Service("questionCollectedService")
public class QuestionCollectedServiceImpl extends AbstractService<QuestionCollected, Long>
		implements QuestionCollectedService {

	@Autowired
	private QuestionCollectedRepository dao;
	
	protected JpaRepository<QuestionCollected, Long> getDao() {
		return dao;
	}
	
	@Override
	public List<QuestionCollected> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

}
