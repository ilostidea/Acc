package com.bit.acc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IQuestionCollectedDao;
import com.bit.acc.model.QuestionCollected;
import com.bit.acc.service.intfs.IQuestionCollectedService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("questionCollectedService")
public class QuestionCollectedService extends AbstractService<QuestionCollected> implements IQuestionCollectedService{
	
	@Resource(name = "questionCollectedDao")
	private IQuestionCollectedDao dao;

	@Override
	protected IOperations<QuestionCollected> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(QuestionCollected transientInstance) {
		super.persist(transientInstance);
	}
	
	public List<QuestionCollected> queryByUser(long userID) {
		return dao.queryByUser(userID);
	}
}
