package com.bit.acc.service;

import com.bit.acc.dao.AnswerDisapprovedRepository;
import com.bit.acc.model.AnswerDisapproved;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AnswerDisapprovedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("answerDisapprovedService")
public class AnswerDisapprovedServiceImpl extends AbstractService<AnswerDisapproved, Long>
		implements AnswerDisapprovedService {
	
	@Autowired
	private AnswerDisapprovedRepository dao;
	
	protected JpaRepository<AnswerDisapproved, Long> getDao() {
		return dao;
	}

	public void delete(AnswerDisapproved answerDisapproved) {
		if(answerDisapproved.getId() != null && answerDisapproved.getId() > 0 ){//如果存在ID，直接根据ID删除
			dao.delete(answerDisapproved);
		} else if( answerDisapproved.getUser() != null && answerDisapproved.getUser().getId() != null && answerDisapproved.getUser().getId() > 0 &&
				answerDisapproved.getAnswer() != null && answerDisapproved.getAnswer().getId() != null && answerDisapproved.getAnswer().getId() > 0 ){//否则根据用户ID和回答ID删除收藏
			dao.deleteAllByAnswerIdAndUserId(answerDisapproved.getAnswer().getId(), answerDisapproved.getUser().getId());
		}
	}

	@Override
	public List<AnswerDisapproved> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

    @Override
	public List<AnswerDisapproved> findByUserAndAnswer(Long userId, Long answerId) {
		return dao.findByUserIdAndAnswerId(userId, answerId);
	}

}
