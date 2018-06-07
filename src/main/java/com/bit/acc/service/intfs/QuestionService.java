package com.bit.acc.service.intfs;

import com.bit.acc.model.Question;
import com.bit.acc.service.baseservice.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface QuestionService extends Service<Question, Long> {
	
	public void switchStatus(Long id, Boolean status);

	public Map<String, Object> findRecent( Long userId, Pageable pageable );
	
	public List<Question> findByCondition(String userName, String question, Boolean status, Boolean accused);

	public List<Question> findByUser(Long userId);

	public List<Question> findByAnsweredUser(Long userId);
	
	public List<Question> findByCollectedUser(Long userId);
	
	public Question getQuesstionAndAnswersById(Long id);
	
	public Question getQuesstionAndAnswersPumpCountByIdForAdmin(Long id);
	
	public Question getQuesstionAndAnswersPumpCountById(Long id, Long userId) ;
	
	public Map<String, Long> getQuestionProfileById(Long userID);

}
