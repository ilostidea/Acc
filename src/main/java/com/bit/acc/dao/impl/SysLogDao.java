package com.bit.acc.dao.impl;

// Generated 2016-6-26 0:57:04 by Hibernate Tools 4.0.0

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.common.db.AbstractDao;
import com.bit.acc.dao.intfs.ISysLogDao;
import com.bit.acc.model.SysLog;

/**
 * Home object for domain model class SysLog.
 * @see com.bit.scm.dao.impl.SysLog
 * @author Hibernate Tools
 */
@Repository("sysLogDao")
public class SysLogDao extends AbstractDao<SysLog> implements ISysLogDao{

	private static final Logger log = LoggerFactory.getLogger(SysLogDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SysLog transientInstance) {
		log.debug("persisting SysLog instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SysLog persistentInstance) {
		log.debug("removing SysLog instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SysLog merge(SysLog detachedInstance) {
		log.debug("merging SysLog instance");
		try {
			SysLog result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysLog findById(int id) {
		log.debug("getting SysLog instance with id: " + id);
		try {
			SysLog instance = entityManager.find(SysLog.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
