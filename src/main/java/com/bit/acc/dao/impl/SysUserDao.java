package com.bit.acc.dao.impl;

// Generated 2016-6-26 0:57:04 by Hibernate Tools 4.0.0

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.ISysUserDao;
import com.bit.acc.model.SysUser;
import com.bit.common.db.AbstractDao;

/**
 * Home object for domain model class SysUser.
 * @see com.bit.scm.dao.impl.SysUser
 * @author Hibernate Tools
 */

@Repository("userDao")
public class SysUserDao extends AbstractDao<SysUser> implements ISysUserDao{

	private static final Logger log = LoggerFactory.getLogger(SysUserDao.class);

	public SysUserDao() {
        super();
        setClazz(SysUser.class);
    }

	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(SysUser transientInstance) {
		log.debug("persisting SysUser instance");
		try {
			super.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SysUser persistentInstance) {
		log.debug("removing SysUser instance");
		try {
			super.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SysUser merge(SysUser detachedInstance) {
		log.debug("merging SysUser instance");
		try {
			SysUser result = super.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysUser findById(Object id) {
		log.debug("getting SysUser instance with id: " + id);
		try {
			String hql = "from SysUser as u where u.id = :id";
			//String hql = "from SysUser as u right outer join fetch u.employee where u.id = :id";
			Query query = entityManager.createQuery(hql);
			query.setParameter("id", id);
			List<SysUser> resultList = (List<SysUser>) query.getResultList();
			//SysUser instance = super.findById(id);
			log.debug("get successful");
			if(resultList != null && resultList.size()>0 ){
				return resultList.get(0);
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	public List<SysUser> findByAccount(String account){
		log.debug("getting SysUser instance with account: " + account);
		try {
			Query query = entityManager.createQuery("select u from SysUser u where u.mobile = :account or u.email = :account");
			//Query query = entityManager.createQuery("select u from SysUser u  right outer join fetch u.employee where u.mobile = :account or email = :account");
			query.setParameter("account", account);
			List<SysUser> listUser = query.getResultList();
			log.debug("get successful");
			return listUser;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public List<SysUser> findAll() {
		log.debug("getting all User");
		try {
			String hql = "from SysUser as u ";
			//String hql = "from SysUser as u left outer join fetch u.employee";
			List<SysUser> resultList = (List<SysUser>) entityManager.createQuery(hql).getResultList();
			log.debug("getting all User successful");
			return resultList;
			//return super.findAll();
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
		//List<SysUser> list = (List<SysUser>) entityManager.createQuery("select u.id, u.mobile, u.email, u.nickName, e.gender from SysUser u inner join u.employee e ").getResultList();
		//List<SysUser> list = (List<SysUser>) entityManager.createQuery("select u from SysUser u").getResultList();
		//return list;
	}
}
