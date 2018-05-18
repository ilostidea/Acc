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
	
	public static final String queryQuestionAnswerCountCollectedTimes = "select new Question( q, "
			+ " ( select count(a1.id) from Answer a1 where a1.question.id = q.id ) as answerCount, "
			+ " ( select count(qc1.id) from QuestionCollected qc1 where qc1.question.id = q.id) as  collectedCount"
			+ "  ) from Question q";
	
	public static final String queryQuestionAnswerPumpCountCollectedTimes = "select q, "
			+ " ( select count(a1.id) from Answer a1 where a1.question.id = q.id ) as answerCount, "
			+ " ( select count(qc1.id) from QuestionCollected qc1 where qc1.question.id = q.id) as  collectedCount"
			+ "  from Question q";
	
	@Modifying
	@Query("update Question set status = ?2 where id = ?1")
	public void switchStatus(Long id, Boolean status);
	
	@Query(value= queryQuestionAnswerPumpCountCollectedTimes + " where q.status is true group by q.id order by q.modifyTime desc",//TODO: What does 'group by q.id' do?
	       countQuery = " select count(q.id) from Question q where q.status is true ")
	@EntityGraph(value = "question.user" , type=EntityGraphType.FETCH)
	public Page<Object> findRecent(Pageable pageable );
	
	public List<Question> findByCondition(Specification<Question> querySpecific);
	
	@Query(queryQuestionAnswerCountCollectedTimes + " where q.status is true and q.user.id = :userID group by q.id order by q.createTime desc")//TODO: What does 'group by q.id' do?
	public List<Question> findByUser(@Param("userID") Long userId);

	@Query(queryQuestionAnswerCountCollectedTimes + " join q.answers a where q.status is true and a.user.id = :userID group by q.id order by q.createTime desc")//TODO: What does 'group by q.id' do?
	public List<Question> findByAnsweredUser(@Param("userID") Long userId);
	
	@Query(queryQuestionAnswerCountCollectedTimes + " join q.questionCollecteds qc where q.status is true and qc.user.id = :userID group by q.id order by q.createTime desc")//TODO: What does 'group by q.id' do?
	public List<Question> findByCollectedUser(@Param("userID") Long userId);
	
	//@QueryHints( { @QueryHint( name = org.hibernate.annotations.QueryHints.FETCHGRAPH, value="question.user") } )
	@Query(queryQuestionAnswerPumpCountCollectedTimes + " where q.status is true and q.id = :id group by q.id")//TODO: What does 'group by q.id' do?
	@EntityGraph(value = "question.user" , type=EntityGraphType.FETCH)
	public Object getQuesstionAndAnswersPumpCountById(@Param("id") Long id) ;
	
	//@QueryHints( { @QueryHint( name = org.hibernate.annotations.QueryHints.FETCHGRAPH, value="question.user") } )
	@Query(queryQuestionAnswerPumpCountCollectedTimes + " where q.id = :id group by q.id")//TODO: What does 'group by q.id' do?
	@EntityGraph(value = "question.user" , type=EntityGraphType.FETCH)
	public Object getQuesstionAndAnswersPumpCountByIdForAdmin(@Param("id") Long id) ;
	
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
