package com.bit.acc.dao.impl;
// Generated 2018-2-3 20:49:54 by Hibernate Tools 5.2.6.Final

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.bit.acc.dao.intfs.IUserSettingDao;
import com.bit.acc.model.UserSetting;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class Usersetting.
 * @see com.bit.acc.dao.impl.UserSetting
 * @author Hibernate Tools
 */
@Repository("userSettingDao")
public class UserSettingDao extends AbstractDao<UserSetting> implements IUserSettingDao{

	private static final Log log = LogFactory.getLog(UserSettingDao.class);

	public UserSettingDao() {
        super();
        setClazz(UserSetting.class);
    }

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserSetting transientInstance) {
		log.debug("persisting Usersetting instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserSetting persistentInstance) {
		log.debug("removing Usersetting instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserSetting merge(UserSetting detachedInstance) {
		log.debug("merging Usersetting instance");
		try {
			UserSetting result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserSetting findById(long id) {
		log.debug("getting Usersetting instance with id: " + id);
		try {
			UserSetting instance = entityManager.find(UserSetting.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<UserSetting> queryByUser( long userID) {
		log.debug("getting UserSetting instances with userID" + userID);
		try {
			String jpql = "select t from " + UserSetting.class.getName() + " as t where t.user.id = :userID";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userID", userID);
			List<UserSetting> resultList = (List<UserSetting>) query.getResultList();
			log.debug("get successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
