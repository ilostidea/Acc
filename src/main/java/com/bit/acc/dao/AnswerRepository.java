package com.bit.acc.dao;

import java.util.List;

import javax.persistence.NamedEntityGraphs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

	//query里面的order by有等于没有，不影响结果，question里面的方法对answer使用compare方法又排序了一下，子对象集合pump根据@OrderBy注解排序
	@Query("select a, " +
			" ( select ac.id from AnswerCollected ac where ac.answer.id = a.id and ac.user.id = :userID) as hasCollected, " +
            " ( select aa.id from AnswerApproved aa where aa.answer.id = a.id and aa.user.id = :userID) as hasApproved, " +
            " ( select ad.id from AnswerDisapproved ad where ad.answer.id = a.id and ad.user.id = :userID) as hasDisapproved " +
            " from Answer a left join fetch a.pumps p left join fetch  p.user where a.status is true and a.question.id = :questionID order by a.createTime desc, p.createTime asc")
	@EntityGraph(value = "answer.user" , type=EntityGraphType.FETCH)
	public List<Object> findByQuestion(@Param("questionID") Long questionId, @Param("userID") Long userId);
	
	/**
	 * 管理员使用的方法，不根据状态过滤
	 * @param questionId
	 * @return
	 */
	@Query("select a from Answer a left join fetch a.pumps p where a.question.id = :questionID order by a.createTime desc, p.createTime desc")
	@EntityGraph(value = "answer.user" , type=EntityGraphType.FETCH)
	public List<Answer> findByQuestionForAdmin(@Param("questionID") Long questionId);
	
	public List<Answer> findByCondition(Specification<Answer> querySpecific);
	
	@Query("select new Answer( a.id, a.question, a.user, a.answer, a.isAnonymous, a.approveCount, a.disapproveCount, a.pumpCount, a.collectedCount, a.isAccused, a.status, a.createTime, a.creator, a.modifyTime, a.modifier, a.question.id, a.question.title ) "
			+ " from Answer a left join a.pumps p where a.status is true and a.user.id = :userID group by a.id order by a.createTime desc")
	public List<Answer> findByUser(@Param("userID") Long userId);
	
	@Query("select new Answer( a.id, a.question, a.user, a.answer, a.isAnonymous, a.approveCount, a.disapproveCount, a.pumpCount, a.collectedCount, a.isAccused, a.status, a.createTime, a.creator, a.modifyTime, a.modifier, a.question.id, a.question.title ) "
			+ " from Answer a left join a.pumps p join a.answerCollecteds ac where a.status is true and ac.user.id = :userID group by a.id order by a.createTime desc")
	public List<Answer> findByCollectedUser(@Param("userID") Long userId);
	
	/**
	 * 管理员使用的方法，不根据状态过滤
	 * @param id
	 * @return
	 */
	@Query("select  a from Answer a join fetch a.question q left join fetch a.pumps p where a.id = :id")
	@EntityGraph(value = "answer.userAndPumps" , type=EntityGraphType.FETCH)
	public Answer getAnswerQuestionPumps(@Param("id") Long id);
	
	@Modifying
	@Query("update Answer set status = ?2 where id = ?1")
	public void switchStatus(Long id, Boolean status);

	@Modifying
	@Query("update Answer set pumpCount = pumpCount + ?2 where id = ?1")
	public void pumpCountAdd(Long id, int count);

	@Modifying
	@Query("update Answer set collectedCount = collectedCount + ?2 where id = ?1")
	public void collectedCountAdd(Long id, int count);
	
	@Modifying
	@Query("update Answer a set a.approveCount = a.approveCount + 1 where a.id = :id")
	public void approveAdd(@Param("id") Long id);

	@Modifying
	@Query("update Answer a set a.approveCount = a.approveCount - 1 where a.id = :id")
	public void approveMinus(@Param("id") Long id);
	
	@Modifying
	@Query("update Answer a set a.disapproveCount = a.disapproveCount + 1 where a.id = :id")
	public void disapproveAdd(@Param("id") Long id);

	@Modifying
	@Query("update Answer a set a.disapproveCount = a.disapproveCount - 1 where a.id = :id")
	public void disapproveMinus(@Param("id") Long id);

}
