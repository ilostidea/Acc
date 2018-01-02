package com.bit.acc.dao.impl;
// default package
// Generated 2017-11-16 0:26:03 by Hibernate Tools 5.2.6.Final

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IMessageDao;
import com.bit.acc.model.Message;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Message.
 * @see com.bit.acc.model.Message
 * @author Hibernate Tools
 */
@Repository("messageDao")
public class MessageDao extends AbstractDao<Message> implements IMessageDao {

	private static final Logger log = LoggerFactory.getLogger(MessageDao.class);

	public MessageDao() {
        super();
        setClazz(Message.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(Message transientInstance) {
		log.debug("persisting Message instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Message persistentInstance) {
		log.debug("removing Message instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Message merge(Message detachedInstance) {
		log.debug("merging Message instance");
		try {
			Message result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Message findById(long id) {
		log.debug("getting Message instance with id: " + id);
		try {
			Message instance = entityManager.find(Message.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	public List<Message> findAll() {
		log.debug("getting all Message");
		try {
			String hql = "from Message as m ";
			//String hql = "from SysUser as u left outer join fetch u.employee";
			List<Message> resultList = (List<Message>) entityManager.createQuery(hql).getResultList();
			log.debug("getting all Message successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
}
