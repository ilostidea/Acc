package com.bit.acc.dao.impl;
// Generated 2018-2-3 20:49:54 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IAnswerDao;
import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;
import com.bit.acc.model.SysUser;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Answer.
 * @see com.bit.acc.dao.impl.Answer
 * @author Hibernate Tools
 */
@Repository("answerDao")
public class AnswerDao extends AbstractDao<Answer> implements IAnswerDao{

	private static final Logger log = LoggerFactory.getLogger(AnswerDao.class);

	public AnswerDao() {
        super();
        setClazz(Answer.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Answer transientInstance) {
		log.debug("persisting Answer instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Answer persistentInstance) {
		log.debug("removing Answer instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Answer merge(Answer detachedInstance) {
		log.debug("merging Answer instance");
		try {
			Answer result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Answer findById(long id) {
		log.debug("getting Answer instance with id: " + id);
		try {
			Answer instance = entityManager.find(Answer.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Answer> queryByQuestion(long questionID) {
		log.debug("getting Answer instances with questionID" + questionID);
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Answer> criteriaQuery = criteriaBuilder.createQuery(Answer.class);
			Root<Answer> root = criteriaQuery.from(Answer.class);
			EntityType<Answer> qestionModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Answer.class,
					root.get( qestionModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "question", Question.class ) ), 
					root.get( qestionModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( qestionModel.getSingularAttribute( "answer", String.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( qestionModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( qestionModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( qestionModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( qestionModel.getSingularAttribute( "modifier", Long.class ) ),
					criteriaBuilder.count( root.join( qestionModel.getSet( "pumps" ), JoinType.LEFT ) )
					) );
			criteriaQuery.where( criteriaBuilder.equal( root.get("question").get( "id" ), questionID) );
			criteriaQuery.groupBy(root.get("id"));
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Answer> result = entityManager.createQuery(criteriaQuery);
			List<Answer> resultList = (List<Answer>) result.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	
	public List<Answer> queryForAdmin(String userName, String answer, Boolean status, Boolean accused) {
		log.debug("getting Answer instances.");
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Answer> criteriaQuery = criteriaBuilder.createQuery(Answer.class);
			Root<Answer> root = criteriaQuery.from(Answer.class);
			//root.fetch( root.getModel().getSingularAttribute("question"), JoinType.INNER);

			EntityType<Answer> answerModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Answer.class,
					root.get( answerModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( answerModel.getSingularAttribute( "question", Question.class ) ), 
					root.get( answerModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( answerModel.getSingularAttribute( "answer", String.class ) ), 
					root.get( answerModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( answerModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( answerModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( answerModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( answerModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( answerModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( answerModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( answerModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( answerModel.getSingularAttribute( "modifier", Long.class ) ),
					criteriaBuilder.count( root.join( answerModel.getSet( "pumps" ), JoinType.LEFT ) ),
					root.get( answerModel.getSingularAttribute( "question", Question.class ) ).get("title")
					) );

			Predicate condition = null;
			if( userName != null && userName.length() > 0 ) {
				condition = criteriaBuilder.or( criteriaBuilder.equal( root.get( "user" ).get("mobile"), userName),
						criteriaBuilder.equal( root.get( "user" ).get("email"), userName ), 
						criteriaBuilder.equal( root.get( "user" ).get("nickName"), userName ) );
			}
			if( answer != null && answer.length() > 0 ) {
				Predicate answerContainsChar =  criteriaBuilder.like( root.get( "answer" ), "%" + answer + "%");
				if(condition != null)
					condition = criteriaBuilder.and( condition, answerContainsChar);
				else
					condition = answerContainsChar;
			}
			if(status != null) {
				Predicate isEnabled = null;
				if( status )
					isEnabled = criteriaBuilder.isTrue( root.get("status") );
				else
					isEnabled = criteriaBuilder.isFalse( root.get("status") );
				if(condition != null)
					condition = criteriaBuilder.and( condition, isEnabled);
				else
					condition = isEnabled;
			}
			if(accused != null) {
				Predicate isAccused = null;
				if ( accused )
					isAccused = criteriaBuilder.isTrue( root.get("isAccused") );
				else
					isAccused = criteriaBuilder.isFalse( root.get("isAccused") );
				if(condition != null)
					condition = criteriaBuilder.and( condition, isAccused);
				else
					condition = isAccused;
			}
            if( condition != null )
            	criteriaQuery.where( condition );
			criteriaQuery.groupBy (root.get("id") );
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Answer> result = entityManager.createQuery(criteriaQuery);
			List<Answer> resultList = (List<Answer>) result.getResultList();
			
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Answer> queryByUser(long userID) {
		log.debug("getting Answer instances with userID" + userID);
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Answer> criteriaQuery = criteriaBuilder.createQuery(Answer.class);
			Root<Answer> root = criteriaQuery.from(Answer.class);
			//root.fetch( root.getModel().getSingularAttribute("question"), JoinType.INNER);

			EntityType<Answer> answerModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Answer.class,
					root.get( answerModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( answerModel.getSingularAttribute( "question", Question.class ) ), 
					root.get( answerModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( answerModel.getSingularAttribute( "answer", String.class ) ), 
					root.get( answerModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( answerModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( answerModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( answerModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( answerModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( answerModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( answerModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( answerModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( answerModel.getSingularAttribute( "modifier", Long.class ) ),
					criteriaBuilder.count( root.join( answerModel.getSet( "pumps" ), JoinType.LEFT ) ),
					root.get( answerModel.getSingularAttribute( "question", Question.class ) ).get("title")
					) );

			//criteriaQuery.multiselect( root,  root.get("question").get("title") );
			criteriaQuery.where( criteriaBuilder.equal( root.get("user").get("id"), userID) );
			criteriaQuery.groupBy (root.get("id") );
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Answer> result = entityManager.createQuery(criteriaQuery);
			List<Answer> resultList = (List<Answer>) result.getResultList();
			
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Answer> queryByCollectedUser(long userID) {
		log.debug("getting Answer instances with collected userID" + userID);
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Answer> criteriaQuery = criteriaBuilder.createQuery(Answer.class);
			Root<Answer> root = criteriaQuery.from(Answer.class);
			//root.fetch( root.getModel().getSingularAttribute("question"), JoinType.INNER);
			EntityType<Answer> answerModel = root.getModel();
			criteriaQuery.select( criteriaBuilder.construct( Answer.class,
					root.get( answerModel.getSingularAttribute( "id", Long.class ) ), 
					root.get( answerModel.getSingularAttribute( "question", Question.class ) ), 
					root.get( answerModel.getSingularAttribute( "user", SysUser.class ) ), 
					root.get( answerModel.getSingularAttribute( "answer", String.class ) ), 
					root.get( answerModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
					root.get( answerModel.getSingularAttribute( "approveCount", Integer.class ) ), 
					root.get( answerModel.getSingularAttribute( "disapproveCount", Integer.class ) ), 
					root.get( answerModel.getSingularAttribute( "isAccused", Boolean.class ) ),
					root.get( answerModel.getSingularAttribute( "status", Boolean.class ) ), 
					root.get( answerModel.getSingularAttribute( "createTime", Date.class ) ), 
					root.get( answerModel.getSingularAttribute( "creator", Long.class ) ), 
					root.get( answerModel.getSingularAttribute( "modifyTime", Date.class ) ), 
					root.get( answerModel.getSingularAttribute( "modifier", Long.class ) ),
					criteriaBuilder.count( root.join( answerModel.getSet( "pumps" ), JoinType.LEFT ) ),
					root.get( answerModel.getSingularAttribute( "question", Question.class ) ).get("title")
					) );
			//criteriaQuery.multiselect( root,  root.get("question").get("title") );
			criteriaQuery.where( criteriaBuilder.equal( root.join( root.getModel().getSet( "answerCollecteds" ) ).get("user").get("id"), userID) );
			criteriaQuery.groupBy (root.get("id") );
			criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
			TypedQuery<Answer> result = entityManager.createQuery(criteriaQuery);
			List<Answer> resultList = (List<Answer>) result.getResultList();
			
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void approve(long id) {
		log.debug( "approve " + id );
		try {
			String jpql = "update " + Answer.class.getName() + " a set a.approveCount = a.approveCount + 1 where a.id = :id";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("id", id);
			query.executeUpdate();
			log.debug("approve successful");
		} catch (RuntimeException re) {
			log.error("approve failed", re);
			throw re;
		}
	}
	
	public void disapprove(long id) {
		log.debug( "approve " + id );
		try {
			String jpql = "update " + Answer.class.getName() + " a set a.disapproveCount = a.disapproveCount + 1 where a.id = :id";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("id", id);
			query.executeUpdate();
			log.debug("approve successful");
		} catch (RuntimeException re) {
			log.error("approve failed", re);
			throw re;
		}
	}
	
}
