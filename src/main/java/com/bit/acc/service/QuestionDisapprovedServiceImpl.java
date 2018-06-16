package com.bit.acc.service;

import com.bit.acc.dao.QuestionDisapprovedRepository;
import com.bit.acc.model.QuestionDisapproved;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.QuestionDisapprovedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionDisapprovedService")
public class QuestionDisapprovedServiceImpl extends AbstractService<QuestionDisapproved, Long>
		implements QuestionDisapprovedService {
	
	@Autowired
	private QuestionDisapprovedRepository dao;
	
	protected JpaRepository<QuestionDisapproved, Long> getDao() {
		return dao;
	}

	public void delete(QuestionDisapproved questionDisapproved) {
		if(questionDisapproved.getId() != null && questionDisapproved.getId() > 0 ){//如果存在ID，直接根据ID删除
			dao.delete(questionDisapproved);
		} else if( questionDisapproved.getUser() != null && questionDisapproved.getUser().getId() != null && questionDisapproved.getUser().getId() > 0 &&
				questionDisapproved.getQuestion() != null && questionDisapproved.getQuestion().getId() != null && questionDisapproved.getQuestion().getId() > 0 ){//否则根据用户ID和回答ID删除收藏
			dao.deleteAllByQuestionIdAndUserId(questionDisapproved.getQuestion().getId(), questionDisapproved.getUser().getId());
		}
	}

	@Override
	public List<QuestionDisapproved> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

	public List<QuestionDisapproved> findByUserAndQuestion(Long userId, Long questionId) {
		return dao.findByUserIdAndQuestionId(userId, questionId);
	}

}
