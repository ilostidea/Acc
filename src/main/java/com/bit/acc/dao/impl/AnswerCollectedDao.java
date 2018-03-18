package com.bit.acc.dao.impl;
// Generated 2018-2-3 20:49:54 by Hibernate Tools 5.2.6.Final

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IAnswerCollectedDao;
import com.bit.acc.model.AnswerCollected;
import com.bit.acc.model.QuestionCollected;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Answercollected.
 * @see com.bit.acc.dao.impl.Answercollected
 * @author Hibernate Tools
 */
@Repository("answerCollectedDao")
public class AnswerCollectedDao extends AbstractDao<AnswerCollected> implements IAnswerCollectedDao{

	private static final Logger log = LoggerFactory.getLogger(AnswerCollectedDao.class);

	public AnswerCollectedDao() {
        super();
        setClazz(AnswerCollected.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AnswerCollected transientInstance) {
		log.debug("persisting Answercollected instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AnswerCollected persistentInstance) {
		log.debug("removing Answercollected instance");
		try {
			if( persistentInstance.getId() > 0 ) {//如果存在ID，直接根据ID删除
			    entityManager.remove(persistentInstance);
			} else if( persistentInstance.getUser().getId() > 0 && persistentInstance.getAnswer().getId() > 0 ){//否则根据用户ID和回答ID删除问题
				CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
				CriteriaDelete<AnswerCollected> deleteQuery = criteriaBuilder.createCriteriaDelete(AnswerCollected.class);
				Root<AnswerCollected> root = deleteQuery.from(AnswerCollected.class);
				//EntityType<QuestionCollected> model = root.getModel();
				deleteQuery.where( criteriaBuilder.and(
						criteriaBuilder.equal( root.get("user").get("id"), persistentInstance.getUser().getId() ),
						criteriaBuilder.equal( root.get("answer").get("id"), persistentInstance.getAnswer().getId() )
						) );
				entityManager.createQuery(deleteQuery).executeUpdate();
			}
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AnswerCollected merge(AnswerCollected detachedInstance) {
		log.debug("merging Answercollected instance");
		try {
			AnswerCollected result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AnswerCollected findById(long id) {
		log.debug("getting Answercollected instance with id: " + id);
		try {
			AnswerCollected instance = entityManager.find(AnswerCollected.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<AnswerCollected> queryByUser( long userID ) {
		log.debug("getting Answer instances with userID" + userID);
		try {
			String jpql = "select t from " + AnswerCollected.class.getName() + " as t join fetch t.answer a join fetch a.question where t.user.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			List<AnswerCollected> resultList = (List<AnswerCollected>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
