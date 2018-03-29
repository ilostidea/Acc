package com.bit.acc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
	
	public static final String queryQuestionAnswerCountCollectedTimes = "select new Question( q.id, q.user, q.title, q.question, q.isAnonymous, q.approveCount, q.disapproveCount, q.isAccused, q.status, q.createTime, q.creator, q.modifyTime, q.modifier, "
			+ " ( select count(a1.id) from Answer a1 where a1.question.id = q.id ) as answerCount, "
			+ " ( select count(qc1.id) from QuestionCollected qc1 where qc1.question.id = q.id) as  collectedCount"
			+ "  ) from Question q ";
	
	@Query(queryQuestionAnswerCountCollectedTimes + " group by q.id order by q.createTime desc")
	public List<Question> queryRecent( );
	
	public List<Question> queryForAdmin(Specification<Question> querySpecific);
	
	@Query(queryQuestionAnswerCountCollectedTimes + " where q.user.id = :userID group by q.id order by q.createTime desc")
	public List<Question> queryByUser(@Param("userID") long userID);

	@Query(queryQuestionAnswerCountCollectedTimes + " join q.answers a where a.user.id = :userID group by q.id order by q.createTime desc")
	public List<Question> queryByAnsweredUser(@Param("userID") long userID);
	
	@Query(queryQuestionAnswerCountCollectedTimes + " join q.questionCollecteds qc where qc.user.id = :userID group by q.id order by q.createTime desc")
	public List<Question> queryByCollectedUser(@Param("userID") long userID);
	
	@Query(queryQuestionAnswerCountCollectedTimes + " where q.id = :id group by q.id")
	public Question getQuesstionAndAnswersPumpCountById(@Param("id") long id) ;
	
	@Query("select t from Question as t left join fetch t.answers where t.id = :id")
	public Question getQuesstionAndAnswersById(@Param("id") long id);
	
	@Query("select new map( " + 
					"(select count(q.id) from Question q where q.user.id = u.id) as myQuestion, " + 
					"(select count(a.id) from Answer a where a.user.id = u.id) as myAnswer, " + 
					"(select count(qc.id) from QuestionCollected qc where qc.user.id = u.id) as myCollectedQuestion, " + 
					"(select count(ac.id) from AnswerCollected ac where ac.user.id = u.id) as myCollectedAnswer, " + 
					"(select count(qc.id) from QuestionCollected qc join Question q on qc.question.id = q.id where q.user.id = u.id) as myQuestionCollectedByOthers, " + 
					"(select count(ac.id) from AnswerCollected ac join Answer a on ac.answer.id = a.id where a.user.id = u.id) as myAnswerCollectedByOthers, " + 
					"(select sum(q.approveCount) from Question q where q.user.id = u.id) + (select sum(a.approveCount) from Answer a where a.user.id = u.id) as approveCount, " + 
					"(select sum(q.disapproveCount) from Question q where q.user.id = u.id) + (select sum(a.disapproveCount) from Answer a where a.user.id = u.id) as disApproveCount " +
					")  " +
					"from SysUser u where u.id = :userID")
	public Map getQuestionProfileById(@Param("userID") long userID);

}
