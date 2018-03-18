package com.bit.acc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IAnswerDao;
import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;
import com.bit.acc.service.intfs.IAnswerService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("answerService")
public class AnswerService extends AbstractService<Answer> implements IAnswerService{
	
	@Resource(name = "answerDao")
	private IAnswerDao dao;
	
	/*@Resource(name = "employeeService")
	private IEmployeeService employeeService;*/

	@Override
	protected IOperations<Answer> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(Answer transientInstance) {
		transientInstance.setApproveCount(0);
		transientInstance.setDisapproveCount(0);
		transientInstance.setIsAccused(false);
		transientInstance.setStatus(true);
		super.persist(transientInstance);
	}

	public List<Answer> queryForAdmin(String userName, String answer, Boolean status, Boolean accused){
		return dao.queryForAdmin(userName, answer, status, accused);
	}

	public List<Answer> queryByUser(long userID) {
		return dao.queryByUser(userID);
	}
	
	public List<Answer> queryByCollectedUser(long userID) {
		return dao.queryByCollectedUser(userID);
	}
	
	public List<Answer> queryByQuestion(long questionID) {
		return dao.queryByQuestion(questionID);
	}
	
	public void approve(long id) {
		dao.approve(id);
	}
	
	public void disapprove(long id) {
		dao.disapprove(id);
	}

}
