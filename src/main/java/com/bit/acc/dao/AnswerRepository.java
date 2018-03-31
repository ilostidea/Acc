package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {
	
	@Query("select new Answer( a.id, a.question, a.user, a.answer, a.isAnonymous, a.approveCount, a.disapproveCount, a.isAccused, a.status, a.createTime, a.creator, a.modifyTime, a.modifier, count(p.id) ) "
			+ " from Answer a left join a.pumps p where a.question.id = :questionID group by a.id order by a.createTime desc")
	public List<Answer> findByQuestion(@Param("questionID") Long questionId);
	
	public List<Answer> findByCondition(Specification<Answer> querySpecific);
	
	@Query("select new Answer( a.id, a.question, a.user, a.answer, a.isAnonymous, a.approveCount, a.disapproveCount, a.isAccused, a.status, a.createTime, a.creator, a.modifyTime, a.modifier, count(p.id), a.question.title ) "
			+ " from Answer a left join a.pumps p where a.user.id = :userID group by a.id order by a.createTime desc")
	public List<Answer> findByUser(@Param("userID") Long userId);
	
	@Query("select new Answer( a.id, a.question, a.user, a.answer, a.isAnonymous, a.approveCount, a.disapproveCount, a.isAccused, a.status, a.createTime, a.creator, a.modifyTime, a.modifier, count(p.id), a.question.title ) "
			+ " from Answer a left join a.pumps p join a.answerCollecteds ac where ac.user.id = :userID group by a.id order by a.createTime desc")
	public List<Answer> findByCollectedUser(@Param("userID") Long userId);
	
	@Modifying
	@Query("update Answer a set a.approveCount = a.approveCount + 1 where a.id = :id")
	public void approve(@Param("id") Long id);
	
	@Modifying
	@Query("update Answer a set a.disapproveCount = a.disapproveCount + 1 where a.id = :id")
	public void disapprove(@Param("id") Long id);

}
