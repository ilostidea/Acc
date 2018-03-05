package com.bit.acc.dao.impl;
// Generated 2018-2-3 20:49:54 by Hibernate Tools 5.2.6.Final

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IPumpDao;
import com.bit.acc.model.Pump;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Pump.
 * @see com.bit.acc.dao.impl.Pump
 * @author Hibernate Tools
 */
@Repository("pumpDao")
public class PumpDao extends AbstractDao<Pump> implements IPumpDao{

	private static final Logger log = LoggerFactory.getLogger(PumpDao.class);

	public PumpDao() {
        super();
        setClazz(Pump.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Pump transientInstance) {
		log.debug("persisting Pump instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Pump persistentInstance) {
		log.debug("removing Pump instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Pump merge(Pump detachedInstance) {
		log.debug("merging Pump instance");
		try {
			Pump result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Pump findById(long id) {
		log.debug("getting Pump instance with id: " + id);
		try {
			Pump instance = entityManager.find(Pump.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Pump> queryByAnswer(long answerID){
		log.debug("getting Pump instances with answerID" + answerID);
		try {
			String jpql = "select t from " + Pump.class.getName() + " as t where t.answer.id = :answerID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("answerID", answerID);
			List<Pump> resultList = (List<Pump>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
