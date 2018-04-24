package com.bit.acc.service.intfs;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.bit.acc.model.Question;
import com.bit.acc.service.baseservice.Service;

public interface QuestionService extends Service<Question, Long> {
	
	public void switchStatus(Long id, Boolean status);

	public List<Question> findRecent( Pageable pageable );
	
	public List<Question> findByCondition(String userName, String question, Boolean status, Boolean accused);

	public List<Question> findByUser(Long userId);

	public List<Question> findByAnsweredUser(Long userId);
	
	public List<Question> findByCollectedUser(Long userId);
	
	public Question getQuesstionAndAnswersById(Long id);
	
	public Question getQuesstionAndAnswersPumpCountByIdForAdmin(Long id);
	
	public Question getQuesstionAndAnswersPumpCountById(Long id) ;
	
	public Map<String, Long> getQuestionProfileById(Long userID);

}
