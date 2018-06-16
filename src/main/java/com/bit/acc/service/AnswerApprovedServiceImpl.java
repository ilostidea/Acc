package com.bit.acc.service;

import com.bit.acc.dao.AnswerApprovedRepository;
import com.bit.acc.model.AnswerApproved;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AnswerApprovedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("answerApprovedService")
public class AnswerApprovedServiceImpl extends AbstractService<AnswerApproved, Long>
		implements AnswerApprovedService {
	
	@Autowired
	private AnswerApprovedRepository dao;
	
	protected JpaRepository<AnswerApproved, Long> getDao() {
		return dao;
	}

	public void delete(AnswerApproved answerApproved) {
		if(answerApproved.getId() != null && answerApproved.getId() > 0 ){//如果存在ID，直接根据ID删除
			dao.delete(answerApproved);
		} else if( answerApproved.getUser() != null && answerApproved.getUser().getId() != null && answerApproved.getUser().getId() > 0 &&
				answerApproved.getAnswer() != null && answerApproved.getAnswer().getId() != null && answerApproved.getAnswer().getId() > 0 ){//否则根据用户ID和回答ID删除收藏
			dao.deleteAllByAnswerIdAndUserId(answerApproved.getAnswer().getId(), answerApproved.getUser().getId());
		}
	}

	@Override
	public List<AnswerApproved> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

	public List<AnswerApproved> findByUserAndAnswer(Long userId, Long answerId) {
		return dao.findByUserIdAndAnswerId(userId, answerId);
	}

}
