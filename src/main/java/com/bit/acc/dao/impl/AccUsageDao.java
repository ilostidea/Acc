package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IAccUsageDao;
import com.bit.acc.model.AccUsage;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class AccUsage.
 * @see com.bit.acc.dao.impl.AccUsage
 * @author Hibernate Tools
 */
@Repository("accUsageDao")
public class AccUsageDao extends AbstractDao<AccUsage> implements IAccUsageDao{

	private static final Logger log = LoggerFactory.getLogger(AccUsageDao.class);

	public AccUsageDao() {
        super();
        setClazz(AccUsage.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AccUsage transientInstance) {
		log.debug("persisting AccUsage instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AccUsage persistentInstance) {
		log.debug("removing AccUsage instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AccUsage merge(AccUsage detachedInstance) {
		log.debug("merging AccUsage instance");
		try {
			AccUsage result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AccUsage findById(long id) {
		log.debug("getting AccUsage instance with id: " + id);
		try {
			AccUsage instance = entityManager.find(AccUsage.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<AccUsage> queryByAcc(Object coaID) {
		log.debug("getting AccUsage instances with coaID" + coaID);
		try {
			String jpql = "select t from " + AccUsage.class.getName() + " as t where t.coa.id = :coaID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("coaID", coaID);
			List<AccUsage> resultList = (List<AccUsage>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
