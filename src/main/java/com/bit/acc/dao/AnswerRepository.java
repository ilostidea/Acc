package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	/*public List<Answer> queryByQuestion(long questionID);
	
	public List<Answer> queryForAdmin(String userName, String answer, Boolean status, Boolean accused);
	
	public List<Answer> queryByUser(long userID);
	
	public List<Answer> queryByCollectedUser(long userID);
	
	public void approve(long id);
	
	public void disapprove(long id);*/

}
