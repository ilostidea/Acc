package com.bit.acc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IAnswerDao;
import com.bit.acc.dao.intfs.IQuestionDao;
import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;
import com.bit.acc.service.intfs.IQuestionService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("questionService")
public class QuestionService extends AbstractService<Question> implements IQuestionService{
	
	@Resource(name = "questionDao")
	private IQuestionDao dao;
	
	@Resource(name = "answerDao")
	private IAnswerDao answerDao;

	@Override
	protected IOperations<Question> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(Question transientInstance) {
		transientInstance.setApproveCount(0);
		transientInstance.setDisapproveCount(0);
		transientInstance.setIsAccused(false);
		transientInstance.setStatus(true);
		super.persist(transientInstance);
	}
	
	public List<Question> queryRecent() {
		return dao.queryRecent( );
	}
	
	public List<Question> queryForAdmin(String userName, String question, Boolean status, Boolean accused) {
		return dao.queryForAdmin(userName, question, status, accused);
	}
	
	public List<Question> queryByUser(long userID) {
		return dao.queryByUser(userID);
	}

	public List<Question> queryByAnsweredUser(long userID) {
		return dao.queryByAnsweredUser(userID);
	}
	
	public List<Question> queryByCollectedUser(long userID) {
		return dao.queryByCollectedUser(userID);
	}

	public Question getQuesstionAndAnswersById(long id) {
		return dao.getQuesstionAndAnswersById(id);
	}
	
	public Question getQuesstionAndAnswersPumpCountById(long id) {
		Question question = dao.getQuesstionAndAnswersPumpCountById(id);
		 List<Answer> answers = answerDao.queryByQuestion(id);
		 question.setAnswers(new HashSet<Answer>(answers) );
		return question;
	}
	
	public Map getQuestionProfileById(long userID) {
		return dao.getQuestionProfileById(userID);
	}
	
}
