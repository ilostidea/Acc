package com.bit.acc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
	
	public static final String queryQuestion = "select q from Question q";

	public static final String queryQuestionAnswersHasCollected = "select q, "
			+ " ( select qc2.id from QuestionCollected qc2 where qc2.question.id = q.id and qc2.user.id = :userID) as hasCollected "
			+ "  from Question q ";
	
	@Modifying
	@Query("update Question set status = ?2 where id = ?1")
	public void switchStatus(Long id, Boolean status);
	
	@Query(value= queryQuestionAnswersHasCollected + " where q.status is true order by q.modifyTime desc",
	       countQuery = " select count(q.id) from Question q where q.status is true ")
	@EntityGraph(value = "question.user" , type=EntityGraphType.FETCH)
	public Page<Object> findRecent(@Param("userID") Long userId, Pageable pageable);
	
	public List<Question> findByCondition(Specification<Question> querySpecific);
	
	@Query(queryQuestion + " where q.status is true and q.user.id = :userID order by q.createTime desc")
	public List<Question> findByUser(@Param("userID") Long userId);

	@Query(queryQuestion + " join q.answers a where q.status is true and a.user.id = :userID order by q.createTime desc")
	public List<Question> findByAnsweredUser(@Param("userID") Long userId);
	
	@Query(queryQuestion + " join q.questionCollecteds qc where q.status is true and qc.user.id = :userID order by q.createTime desc")
	public List<Question> findByCollectedUser(@Param("userID") Long userId);
	
	//@QueryHints( { @QueryHint( name = org.hibernate.annotations.QueryHints.FETCHGRAPH, value="question.user") } )
	@Query(queryQuestionAnswersHasCollected + " where q.status is true and q.id = :id")
	@EntityGraph(value = "question.user" , type=EntityGraphType.FETCH)
	public Object getQuesstionAnswersByIdAndUser(@Param("id") Long id, @Param("userID") Long userId) ;
	
	//@QueryHints( { @QueryHint( name = org.hibernate.annotations.QueryHints.FETCHGRAPH, value="question.user") } )
	@Query(queryQuestionAnswersHasCollected + " where q.id = :id")
	@EntityGraph(value = "question.user" , type=EntityGraphType.FETCH)
	public Object getQuesstionAnswersByIdAndUserForAdmin(@Param("id") Long id, @Param("userID") Long userId) ;
	
	@Query("select t from Question as t left join fetch t.answers where t.id = :id")
	public Question getQuesstionAndAnswersById(@Param("id") Long id);
	
	@Query("select new map( " + 
					"(select count(q.id) from Question q where q.status is true and q.user.id = u.id) as myQuestion, " + 
					"(select count(a.id) from Answer a where a.status is true and a.user.id = u.id) as myAnswer, " + 
					"(select count(qc.id) from QuestionCollected qc where qc.user.id = u.id) as myCollectedQuestion, " + 
					"(select count(ac.id) from AnswerCollected ac where ac.user.id = u.id) as myCollectedAnswer, " + 
					"(select count(qc.id) from QuestionCollected qc join Question q on qc.question.id = q.id where q.user.id = u.id) as myQuestionCollectedByOthers, " + 
					"(select count(ac.id) from AnswerCollected ac join Answer a on ac.answer.id = a.id where a.user.id = u.id) as myAnswerCollectedByOthers, " + 
					"(select Coalesce( sum(q.approveCount), 0 ) from Question q where q.status is true and q.user.id = u.id) + (select Coalesce( sum(a.approveCount), 0 ) from Answer a where a.status is true and a.user.id = u.id) as approveCount, " + 
					"(select Coalesce( sum(q.disapproveCount), 0 ) from Question q where q.status is true and q.user.id = u.id) + (select Coalesce( sum(a.disapproveCount), 0 ) from Answer a where a.status is true and a.user.id = u.id) as disApproveCount " +
					")  " +
					"from SysUser u where u.id = :userID")
	public Map<String, Long> getQuestionProfileById(@Param("userID") Long userId);

}
