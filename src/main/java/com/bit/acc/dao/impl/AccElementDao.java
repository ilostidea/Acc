package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IAccElementDao;
import com.bit.acc.model.AccElement;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Coa.
 * @see com.bit.acc.dao.impl.AccElement
 * @author Hibernate Tools
 */
@Repository("accElementDao")
public class AccElementDao extends AbstractDao<AccElement> implements IAccElementDao{

	private static final Logger log = LoggerFactory.getLogger(AccElementDao.class);

	public AccElementDao() {
        super();
        setClazz(AccElement.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AccElement transientInstance) {
		log.debug("persisting AccElement instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AccElement persistentInstance) {
		log.debug("removing AccElement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AccElement merge(AccElement detachedInstance) {
		log.debug("merging AccElement instance");
		try {
			AccElement result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AccElement findById(long id) {
		log.debug("getting AccElement instance with id: " + id);
		try {
			AccElement instance = entityManager.find(AccElement.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<AccElement> queryByAccStandard(Object accountingStandardID) {
		log.debug("getting AccElement instances with accountingStandardID" + accountingStandardID);
		try {
			String jpql = "select t from " + AccElement.class.getName() + " as t where t.accountingStandard.id = :accountingStandardID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardID", accountingStandardID);
			List<AccElement> resultList = (List<AccElement>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<AccElement> queryByAccStandard(String accountingStandardCode, int exeYear) {
		log.debug("getting AccElement instances with accountingStandardCode" + accountingStandardCode + " and its exeYear" + exeYear);
		try {
			String jpql = "select t from " + AccElement.class.getName() + " as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			List<AccElement> resultList = (List<AccElement>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
