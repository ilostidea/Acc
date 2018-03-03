package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IGeneralPrincipleDao;
import com.bit.acc.model.GeneralPrinciple;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Generalprinciple.
 * @see com.bit.acc.dao.impl.GeneralPrinciple
 * @author Hibernate Tools
 */
@Repository("generalPrincipleDao")
public class GeneralPrincipleDao extends AbstractDao<GeneralPrinciple> implements IGeneralPrincipleDao{

	private static final Logger log = LoggerFactory.getLogger(GeneralPrincipleDao.class);

	public GeneralPrincipleDao() {
        super();
        setClazz(GeneralPrinciple.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(GeneralPrinciple transientInstance) {
		log.debug("persisting Generalprinciple instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(GeneralPrinciple persistentInstance) {
		log.debug("removing Generalprinciple instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public GeneralPrinciple merge(GeneralPrinciple detachedInstance) {
		log.debug("merging Generalprinciple instance");
		try {
			GeneralPrinciple result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GeneralPrinciple findById(long id) {
		log.debug("getting Generalprinciple instance with id: " + id);
		try {
			GeneralPrinciple instance = entityManager.find(GeneralPrinciple.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<GeneralPrinciple> queryByAccStandard(Object accountingStandardID) {
		log.debug("getting Generalprinciple instances with accountingStandardID" + accountingStandardID);
		try {
			String jpql = "select t from " + GeneralPrinciple.class.getName() + " as t where t.accountingStandard.id = :accountingStandardID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardID", accountingStandardID);
			List<GeneralPrinciple> resultList = (List<GeneralPrinciple>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<GeneralPrinciple> queryByAccStandard(String accountingStandardCode, int exeYear) {
		log.debug("getting Generalprinciple instances with accountingStandardCode" + accountingStandardCode + " and its exeYear" + exeYear);
		try {
			String jpql = "select t from " + GeneralPrinciple.class.getName() + " as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			List<GeneralPrinciple> resultList = (List<GeneralPrinciple>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
