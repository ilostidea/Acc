package com.bit.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IAccountingStandardDao;
import com.bit.acc.model.AccountingStandard;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Accountingstandard.
 * @see com.bit.acc.dao.impl.AccountingStandard
 * @author Hibernate Tools
 */
@Repository("accountingStandardDao")
public class AccountingStandardDao extends AbstractDao<AccountingStandard> implements IAccountingStandardDao{

	private static final Logger log = LoggerFactory.getLogger(AccountingStandardDao.class);

	public AccountingStandardDao() {
        super();
        setClazz(AccountingStandard.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AccountingStandard transientInstance) {
		log.debug("persisting Accountingstandard instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AccountingStandard persistentInstance) {
		log.debug("removing Accountingstandard instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AccountingStandard merge(AccountingStandard detachedInstance) {
		log.debug("merging Accountingstandard instance");
		try {
			AccountingStandard result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AccountingStandard findById(long id) {
		log.debug("getting Accountingstandard instance with id: " + id);
		try {
			AccountingStandard instance = entityManager.find( AccountingStandard.class, id );
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@Override
	public List<String[]> getDistinctNameCode() {
		log.debug("getting Accountingstandard instances with distinct Name and Code. ");
		try {
			String jpql = "select distinct t.name, t.code from " + AccountingStandard.class.getName() + " as t";
			List<String[]> resultList = (List<String[]>) entityManager.createQuery(jpql).getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
