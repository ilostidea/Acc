package com.bit.acc.service;

import com.bit.acc.dao.AnswerCollectedRepository;
import com.bit.acc.model.AnswerCollected;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AnswerCollectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("answerCollectedService")
public class AnswerCollectedServiceImpl extends AbstractService<AnswerCollected, Long>
		implements AnswerCollectedService {
	
	@Autowired
	private AnswerCollectedRepository dao;
	
	protected JpaRepository<AnswerCollected, Long> getDao() {
		return dao;
	}

	public void delete(AnswerCollected answerCollected) {
		if(answerCollected.getId() != null && answerCollected.getId() > 0 ){//如果存在ID，直接根据ID删除
			dao.delete(answerCollected);
		} else if( answerCollected.getUser() != null && answerCollected.getUser().getId() != null && answerCollected.getUser().getId() > 0 &&
				answerCollected.getAnswer() != null && answerCollected.getAnswer().getId() != null && answerCollected.getAnswer().getId() > 0 ){//否则根据用户ID和回答ID删除收藏
			dao.deleteAllByAnswerIdAndUserId(answerCollected.getAnswer().getId(), answerCollected.getUser().getId());
		}
	}

	@Override
	public List<AnswerCollected> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

}
