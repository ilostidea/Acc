package com.bit.acc.dao.impl;
// Generated 2018-2-3 20:49:54 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IQuestionDao;
import com.bit.acc.model.Question;
import com.bit.acc.model.SysUser;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Question.
 * @see com.bit.acc.dao.impl.Question
 * @author Hibernate Tools
 */
@Repository("questionDao")
public class QuestionDao extends AbstractDao<Question> implements IQuestionDao{

	private static final Logger log = LoggerFactory.getLogger(QuestionDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	public QuestionDao() {
        super();
        setClazz(Question.class);
    }

	public void persist(Question transientInstance) {
		log.debug("persisting Question instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Question persistentInstance) {
		log.debug("removing Question instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	
	/*@Override
	public void remove(long id) {
		log.debug("removing Question instance");
		try {
			super.remove(id);
			entityManager.flush();//spring data jpa 在执行操作时是把数据缓存起来，没有提交到数据库，需要flush才可以捕获异常
			log.debug("remove successful");
		} catch (Exception re) {
			log.error("remove failed", re);
			throw re;
		}
	}*/

	public Question merge(Question detachedInstance) {
		log.debug("merging Question instance");
		try {
			Question result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Question findById(long id) {
		log.debug("getting Question instance with id: " + id);
		try {
			Question instance = entityManager.find(Question.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Question> queryByUser(long userID) {
		log.debug("getting Question instances with userID" + userID);
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
			Root<Question> root = criteriaQuery.from(Question.class);
			//root.fetch( root.getModel().getSet("answers"), JoinType.LEFT);

			EntityType<Question> qestionModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Question.class,
					root.get( qestionModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( qestionModel.getSingularAttribute( "title", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "question", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( qestionModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifier", Long.class ) ),/*
					criteriaBuilder.count( criteriaQuery.from( Answer.class ) ),
					criteriaBuilder.count( criteriaQuery.from( QuestionCollected.class )*/
					criteriaBuilder.count( root.join( qestionModel.getSet( "answers" ), JoinType.LEFT ) ),
					criteriaBuilder.count( root.join( qestionModel.getSet("questionCollecteds"), JoinType.LEFT ) )
					) );
			criteriaQuery.where( criteriaBuilder.equal( root.get( "user" ).get("id"), userID) );
			criteriaQuery.groupBy(root.get("id"));
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Question> result = entityManager.createQuery(criteriaQuery);
			List<Question> resultList = (List<Question>) result.getResultList();
			
			/*String jpql = "select t from " + Question.class.getName() + " as t where t.user.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			List<Question> resultList = (List<Question>) query.getResultList();*/
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Question> queryByAnsweredUser(long userID) {
		log.debug("getting Question instances with answered userID" + userID);
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
			Root<Question> root = criteriaQuery.from(Question.class);
			//root.fetch( root.getModel().getSet("answers"), JoinType.LEFT);

			EntityType<Question> qestionModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Question.class,
					root.get( qestionModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( qestionModel.getSingularAttribute( "title", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "question", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( qestionModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifier", Long.class ) ),
					criteriaBuilder.count( root.join( qestionModel.getSet( "answers" ), JoinType.LEFT ) ),
					criteriaBuilder.count( root.join( qestionModel.getSet("questionCollecteds"), JoinType.LEFT ) )
					) );
			criteriaQuery.where( criteriaBuilder.equal( root.join( qestionModel.getSet( "answers" ) ).get("user").get("id"), userID) );
			criteriaQuery.groupBy(root.get("id"));
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Question> result = entityManager.createQuery(criteriaQuery);
			List<Question> resultList = (List<Question>) result.getResultList();
			
			/*String jpql = "select t from " + Question.class.getName() + " as t join t.answers a where a.user.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			List<Question> resultList = (List<Question>) query.getResultList();*/
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Question> queryByCollectedUser(long userID) {
		log.debug("getting Question instances with collected userID" + userID);
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
			Root<Question> root = criteriaQuery.from(Question.class);
			//root.fetch( root.getModel().getSet("answers"), JoinType.LEFT);

			EntityType<Question> qestionModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Question.class,
					root.get( qestionModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( qestionModel.getSingularAttribute( "title", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "question", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( qestionModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifier", Long.class ) ),
					criteriaBuilder.count( root.join( qestionModel.getSet( "answers" ), JoinType.LEFT ) ),
					criteriaBuilder.count( root.join( qestionModel.getSet("questionCollecteds"), JoinType.LEFT ) )
					) );
			criteriaQuery.where( criteriaBuilder.equal( root.join( qestionModel.getSet( "questionCollecteds" ) ).get("user").get("id"), userID) );
			criteriaQuery.groupBy(root.get("id"));
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Question> result = entityManager.createQuery(criteriaQuery);
			List<Question> resultList = (List<Question>) result.getResultList();
			
			/*String jpql = "select t from " + Question.class.getName() + " as t join t.questionCollecteds c where c.user.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			List<Question> resultList = (List<Question>) query.getResultList();*/
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Question getQuesstionAndAnswersById(long id) {
		log.debug("getting Question and its Answers instance with id: " + id);
		try {
			/*//Question instance = entityManager.find(Question.class, id);
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
			Root<Question> root = criteriaQuery.from(Question.class);
			//Join<Question, Answer> leftJoin = root.join("answers", JoinType.LEFT);
			//root.fetch("answers", JoinType.LEFT);
			//criteriaQuery.where(criteriaBuilder.equal( leftJoin.get("question").get( "id" ), id) );
			EntityType<Question> qestionModel = root.getModel();
			root.fetch( qestionModel.getSet("answers"), JoinType.LEFT );
			//Join<Question, Answer> join = root.join( qestionModel.getSet("answers", Answer.class), JoinType.LEFT );
			criteriaQuery.select( root );
			criteriaQuery.where( criteriaBuilder.equal( root.get( qestionModel.getSingularAttribute("id", Long.class) ), 1) );
			TypedQuery<Question> result = entityManager.createQuery(criteriaQuery);*/
			
			String jpql = "select t from " + Question.class.getName() + " as t left join fetch t.answers where t.id = :id";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("id", id);
			
			log.debug("get successful");
			return (Question) query.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Map getQuestionProfileById(long userID) {
		log.debug("getting Question and its Answers instance with id: " + userID);
		try {
			String jpql = "select new map( " + 
					"(select count(q.id) from Question q where q.user.id = u.id) as myQuestion, " + 
					"(select count(a.id) from Answer a where a.user.id = u.id) as myAnswer, " + 
					"(select count(qc.id) from QuestionCollected qc where qc.user.id = u.id) as myCollectedQuestion, " + 
					"(select count(ac.id) from AnswerCollected ac where ac.user.id = u.id) as myCollectedAnswer, " + 
					"(select count(qc.id) from QuestionCollected qc join Question q on qc.question.id = q.id where q.user.id = u.id) as myQuestionCollectedByOthers, " + 
					"(select count(ac.id) from AnswerCollected ac join Answer a on ac.answer.id = a.id where a.user.id = u.id) as myAnswerCollectedByOthers, " + 
					"(select sum(q.approveCount) from Question q where q.user.id = u.id) + (select sum(a.approveCount) from Answer a where a.user.id = u.id) as approveCount, " + 
					"(select sum(q.disapproveCount) from Question q where q.user.id = u.id) + (select sum(a.disapproveCount) from Answer a where a.user.id = u.id) as disApproveCount " +
					")  " +
					"from SysUser u where u.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			//query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(QuestionProfile.class));
			log.debug("get successful");
			Map map = (Map) query.getSingleResult();
			return map;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
