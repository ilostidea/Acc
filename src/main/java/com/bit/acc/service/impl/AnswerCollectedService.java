package com.bit.acc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IAnswerCollectedDao;
import com.bit.acc.model.AnswerCollected;
import com.bit.acc.service.intfs.IAnswerCollectedService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("answerCollectedService")
public class AnswerCollectedService extends AbstractService<AnswerCollected> implements IAnswerCollectedService{
	
	@Resource(name = "answerCollectedDao")
	private IAnswerCollectedDao dao;

	@Override
	protected IOperations<AnswerCollected> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(AnswerCollected transientInstance) {
		super.persist(transientInstance);
	}
	
	public List<AnswerCollected> queryByUser( long userID ) {
		return dao.queryByUser(userID);
	}
	
}
