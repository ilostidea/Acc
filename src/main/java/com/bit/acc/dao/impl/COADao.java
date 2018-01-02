package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.ICOADao;
import com.bit.acc.model.COA;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Coa.
 * @see com.bit.acc.dao.impl.COA
 * @author Hibernate Tools
 */
@Repository("coaDao")
public class COADao extends AbstractDao<COA> implements ICOADao{

	private static final Logger log = LoggerFactory.getLogger(COADao.class);

	public COADao() {
        super();
        setClazz(COA.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(COA transientInstance) {
		log.debug("persisting Coa instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(COA persistentInstance) {
		log.debug("removing Coa instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public COA merge(COA detachedInstance) {
		log.debug("merging Coa instance");
		try {
			COA result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public COA findById(long id) {
		log.debug("getting Coa instance with id: " + id);
		try {
			COA instance = entityManager.find(COA.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<COA> queryByAccStandard(Object accountingStandardID) {
		log.debug("getting COA instances with accountingStandardID" + accountingStandardID);
		try {
			String jpql = "select t from " + COA.class.getName() + " as t where t.accountingStandard.id = :accountingStandardID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardID", accountingStandardID);
			List<COA> resultList = (List<COA>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<COA> queryByAccStandard(String accountingStandardCode, int exeYear) {
		log.debug("getting COA instances with accountingStandardCode" + accountingStandardCode + " and its exeYear" + exeYear);
		try {
			/*
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<COA> criteriaQuery = criteriaBuilder.createQuery(COA.class);
			Root<COA> root = criteriaQuery.from(COA.class);
			//Predicate condition = criteriaBuilder.gt(root.get(Employee_.age), 24);
			criteriaQuery.where();
			return entityManager.createQuery(criteriaQuery).getResultList();*/
			
			String jpql = "select t from " + COA.class.getName() + " as t left join fetch t.accUsages where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			List<COA> resultList = (List<COA>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<COA> queryByAccStandardElement(String accountingStandardCode, int exeYear, String elementCode) {
		log.debug("getting COA instances with accountingStandardCode" + accountingStandardCode + " , its exeYear" + exeYear + "and elementCode " + elementCode);
		try {
			String jpql = "select t from " + COA.class.getName() + " as t left join fetch t.accUsages where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear and t.elementCode = :elementCode";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			query.setParameter("elementCode", elementCode);
			List<COA> resultList = (List<COA>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}
