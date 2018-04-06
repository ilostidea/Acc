package com.bit.common.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {

	@Autowired
	private RedisTemplate<String, Session> redisTemplate;

	private static final String KEY = "shareSessionMap";

	@Override
	public Collection<Session> getActiveSessions() {
		System.out.println("get active sessions");
		List<Session> list = new ArrayList<>();
		List<Session> cacheSessions = (List<Session>) super.getActiveSessions() ;
		BoundHashOperations<String, String, Session> ops = redisTemplate.boundHashOps(KEY);
		for(Session session : cacheSessions) {
			if( !ops.keys().contains( session.getId().toString() ) ) {
				list.add(session);
			}
		}
		list.addAll( ops.values() );
		return list;
	}

	@Override
	protected Serializable doCreate(Session session) {
		System.out.println("do create");
		/*
		 * Serializable sessionId = generateSessionId(session); 
		 * assignSessionId(session, sessionId);
		 */
		Serializable sessionId = super.doCreate(session);
		redisTemplate.boundHashOps(KEY).put(sessionId.toString(), session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// 先从缓存中获取session，如果没有再去数据库中获取
		System.out.println("want to read");
		Session session = super.doReadSession(sessionId);
		if (session == null) {
			System.out.println("read");
			session = (Session) redisTemplate.boundHashOps(KEY).get(sessionId.toString());
		}
		return session;
	}

	@Override
	public void doUpdate(Session session) throws UnknownSessionException {
		super.doUpdate(session);//父类实际上什么都没做
		System.out.println("want to update");
		if (session instanceof ValidatingSession && !( (ValidatingSession) session ).isValid()) {
			return;
		}
		if (session instanceof ShiroSession) {
			// 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
			ShiroSession shiroSession = (ShiroSession) session;
			if ( !shiroSession.isChanged() ) {
				return;
			}
			System.out.println("update");
			Serializable sessionId = session.getId();
			redisTemplate.boundHashOps(KEY).put(sessionId.toString(), session);
			shiroSession.setChanged(false);
		}
	}

	@Override
	public void doDelete(Session session) {
		System.out.println("want to delete");
		super.doDelete(session);
		if (session != null && session.getId() != null) {
			System.out.println("delete");
			redisTemplate.boundHashOps(KEY).delete(session.getId().toString());
		}
	}

}
