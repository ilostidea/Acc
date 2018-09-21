package com.bit.acc.service;

import com.bit.acc.dao.QuestionCollectedRepository;
import com.bit.acc.dao.QuestionRepository;
import com.bit.acc.model.QuestionCollected;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.QuestionCollectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionCollectedService")
public class QuestionCollectedServiceImpl extends AbstractService<QuestionCollected, Long>
		implements QuestionCollectedService {

	@Autowired
	private QuestionCollectedRepository dao;

	@Autowired
	private QuestionRepository questionDao;
	
	protected JpaRepository<QuestionCollected, Long> getDao() {
		return dao;
	}

	@Override
	public <S extends QuestionCollected> S save(S entity) {
	    if(entity.getId() == null){
	        questionDao.collectedCountAdd(entity.getQuestion().getId(), 1);
        }
		return super.save(entity);
	}

	public void delete(QuestionCollected questionCollected) {
		if(questionCollected.getId() != null && questionCollected.getId() > 0 ){//如果存在ID，直接根据ID删除
			dao.delete(questionCollected);
        } else if( questionCollected.getUser() != null && questionCollected.getUser().getId() != null && questionCollected.getUser().getId() > 0 &&
                questionCollected.getQuestion() != null && questionCollected.getQuestion().getId() != null && questionCollected.getQuestion().getId() > 0 ){//否则根据用户ID和问题ID取消关注问题
		    dao.deleteAllByQuestionIdAndUserId(questionCollected.getQuestion().getId(), questionCollected.getUser().getId());
        }
        questionDao.collectedCountAdd(questionCollected.getQuestion().getId(), -1);
	}

	/**
	 * 根据用户id取消收藏
	 * @param questionCollected
	 * @param userId
	 */
	public void deleteByUserId(QuestionCollected questionCollected, Long userId) {
		dao.deleteAllByQuestionIdAndUserId(questionCollected.getQuestion().getId(), userId);
		questionDao.collectedCountAdd(questionCollected.getQuestion().getId(), -1);
	}
	
	@Override
	public List<QuestionCollected> findByUser(Long userId) {
		return dao.findByUserId(userId);
	}

}
