package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.ISpecificStandardDao;
import com.bit.acc.model.SpecificStandard;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Specificstandard.
 * @see com.bit.acc.dao.impl.SpecificStandard
 * @author Hibernate Tools
 */
@Repository("specificStandardDao")
public class SpecificStandardDao extends AbstractDao<SpecificStandard> implements ISpecificStandardDao{

	private static final Logger log = LoggerFactory.getLogger(SpecificStandardDao.class);

	public SpecificStandardDao() {
        super();
        setClazz(SpecificStandard.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SpecificStandard transientInstance) {
		log.debug("persisting Specificstandard instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SpecificStandard persistentInstance) {
		log.debug("removing Specificstandard instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SpecificStandard merge(SpecificStandard detachedInstance) {
		log.debug("merging Specificstandard instance");
		try {
			SpecificStandard result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SpecificStandard findById(long id) {
		log.debug("getting Specificstandard instance with id: " + id);
		try {
			SpecificStandard instance = entityManager.find(SpecificStandard.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<SpecificStandard> queryByAccStandard(Object accountingStandardID) {
		log.debug("getting SpecificStandard instances with accountingStandardID" + accountingStandardID);
		try {
			String jpql = "select t from " + SpecificStandard.class.getName() + " as t where t.accountingStandard.id = :accountingStandardID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardID", accountingStandardID);
			List<SpecificStandard> resultList = (List<SpecificStandard>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<SpecificStandard> queryByAccStandard(String accountingStandardCode, int exeYear) {
		log.debug("getting SpecificStandard instances with accountingStandardCode" + accountingStandardCode + " and its exeYear" + exeYear);
		try {
			String jpql = "select t from " + SpecificStandard.class.getName() + " as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			List<SpecificStandard> resultList = (List<SpecificStandard>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<SpecificStandard> getTitlesByAccStandard(String accountingStandardCode, int exeYear) {
		log.debug("getting SpecificStandard titles with accountingStandardCode" + accountingStandardCode + " and its exeYear" + exeYear);
		try {
			String jpql = "select new SpecificStandard(t.id, t.isPreface, t.title, " + 
					"CASE t.isPreface WHEN true THEN t.specifics ELSE null END " + 
					") from " + SpecificStandard.class.getName() + " as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			List<SpecificStandard> resultList = (List<SpecificStandard>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}
