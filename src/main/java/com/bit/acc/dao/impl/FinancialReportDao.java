package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IFinancialReportDao;
import com.bit.acc.model.FinancialReport;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Financialreport.
 * @see com.bit.acc.dao.impl.FinancialReport
 * @author Hibernate Tools
 */
@Repository("financialReportDao")
public class FinancialReportDao extends AbstractDao<FinancialReport> implements IFinancialReportDao{

	private static final Logger log = LoggerFactory.getLogger(FinancialReportDao.class);

	public FinancialReportDao() {
        super();
        setClazz(FinancialReport.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(FinancialReport transientInstance) {
		log.debug("persisting FinancialReport instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(FinancialReport persistentInstance) {
		log.debug("removing FinancialReport instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public FinancialReport merge(FinancialReport detachedInstance) {
		log.debug("merging FinancialReport instance");
		try {
			FinancialReport result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FinancialReport findById(long id) {
		log.debug("getting FinancialReport instance with id: " + id);
		try {
			FinancialReport instance = entityManager.find( FinancialReport.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<FinancialReport> queryByAccStandard(Object accountingStandardID) {
		log.debug("getting FinancialReport instances with accountingStandardID" + accountingStandardID);
		try {
			String jpql = "select t from " + FinancialReport.class.getName() + " as t where t.accountingStandard.id = :accountingStandardID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardID", accountingStandardID);
			List<FinancialReport> resultList = (List<FinancialReport>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<FinancialReport> queryByAccStandard(String accountingStandardCode, int exeYear) {
		log.debug("getting FinancialReport instances with accountingStandardCode" + accountingStandardCode + " and its exeYear" + exeYear);
		try {
			String jpql = "select t from " + FinancialReport.class.getName() + " as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("accountingStandardCode", accountingStandardCode);
			query.setParameter("exeYear", exeYear);
			List<FinancialReport> resultList = (List<FinancialReport>) query.getResultList();
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
