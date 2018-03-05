package com.bit.acc.dao.impl;
// Generated 2018-2-3 20:49:54 by Hibernate Tools 5.2.6.Final

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IAnswerDao;
import com.bit.acc.model.Answer;
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
			String jpql = "select t from " + Answer.class.getName() + " as t where t.question.id = :questionID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("questionID", questionID);
			List<Answer> resultList = (List<Answer>) query.getResultList();
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
