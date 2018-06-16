package com.bit.acc.service;

import com.bit.acc.dao.QuestionApprovedRepository;
import com.bit.acc.model.QuestionApproved;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.QuestionApprovedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionApprovedService")
public class QuestionApprovedServiceImpl extends AbstractService<QuestionApproved, Long>
		implements QuestionApprovedService {
	
	@Autowired
	private QuestionApprovedRepository dao;
	
	protected JpaRepository<QuestionApproved, Long> getDao() {
		return dao;
	}

	public void delete(QuestionApproved questionApproved) {
		if(questionApproved.getId() != null && questionApproved.getId() > 0 ){//如果存在ID，直接根据ID删除
			dao.delete(questionApproved);
		} else if( questionApproved.getUser() != null && questionApproved.getUser().getId() != null && questionApproved.getUser().getId() > 0 &&
				questionApproved.getQuestion() != null && questionApproved.getQuestion().getId() != null && questionApproved.getQuestion().getId() > 0 ){//否则根据用户ID和回答ID删除收藏
			dao.deleteAllByQuestionIdAndUserId(questionApproved.getQuestion().getId(), questionApproved.getUser().getId());
		}
	}

	@Override
	public List<QuestionApproved> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

	@Override
	public List<QuestionApproved> findByUserAndQuestion(Long userId, Long questionId){
		return  dao.findByUserIdAndQuestionId(userId, questionId);
	}

}
