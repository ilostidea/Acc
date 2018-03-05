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

import com.bit.acc.dao.intfs.IQuestionCollectedDao;
import com.bit.acc.model.QuestionCollected;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Questioncollected.
 * @see com.bit.acc.dao.impl.Questioncollected
 * @author Hibernate Tools
 */
@Repository("questionCollectedDao")
public class QuestionCollectedDao extends AbstractDao<QuestionCollected> implements IQuestionCollectedDao{

	private static final Logger log = LoggerFactory.getLogger(QuestionCollectedDao.class);

	public QuestionCollectedDao() {
        super();
        setClazz(QuestionCollected.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(QuestionCollected transientInstance) {
		log.debug("persisting Questioncollected instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(QuestionCollected persistentInstance) {
		log.debug("removing Questioncollected instance");
		try {
			if( persistentInstance.getId() > 0 ) {//如果存在ID，直接根据ID删除
				entityManager.remove(persistentInstance);
			} else if( persistentInstance.getUser().getId() > 0 && persistentInstance.getQuestion().getId() > 0 ){//否则根据用户ID和问题ID删除问题
				CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
				CriteriaDelete<QuestionCollected> deleteQuery = criteriaBuilder.createCriteriaDelete(QuestionCollected.class);
				Root<QuestionCollected> root = deleteQuery.from(QuestionCollected.class);
				//EntityType<QuestionCollected> model = root.getModel();
				deleteQuery.where( criteriaBuilder.and(
						criteriaBuilder.equal( root.get("user").get("id"), persistentInstance.getUser().getId() ),
						criteriaBuilder.equal( root.get("question").get("id"), persistentInstance.getQuestion().getId() )
						) );
				entityManager.createQuery(deleteQuery).executeUpdate();
			}
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public QuestionCollected merge(QuestionCollected detachedInstance) {
		log.debug("merging Questioncollected instance");
		try {
			QuestionCollected result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public QuestionCollected findById(long id) {
		log.debug("getting Questioncollected instance with id: " + id);
		try {
			QuestionCollected instance = entityManager.find(QuestionCollected.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<QuestionCollected> queryByUser(long userID) {
		log.debug("getting QuestionCollected instances with userID" + userID);
		try {
			String jpql = "select t from " + QuestionCollected.class.getName() + " as t where t.user.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			List<QuestionCollected> resultList = (List<QuestionCollected>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}
