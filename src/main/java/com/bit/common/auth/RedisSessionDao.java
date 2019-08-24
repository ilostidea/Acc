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
		System.out.println("RedisSessionDao:getActiveSessions will get active sessions in cache and redis. ");
		List<Session> list = new ArrayList<>();
		List<Session> cacheSessions = (List<Session>) super.getActiveSessions() ;
		for( Session s: cacheSessions) {
			System.out.println("RedisSessionDao:getActiveSessions has gotton cache session -> " + s.getId() + " : " + s.toString() );
		}
		BoundHashOperations<String, String, Session> ops = redisTemplate.boundHashOps(KEY);
		for( Session s: ops.values()) {
			System.out.println("RedisSessionDao:getActiveSessions has gotton redis session -> " + s.getId() + " : " + s.toString() );
		}
		for(Session session : cacheSessions) {
			if( !ops.keys().contains( session.getId().toString() ) ) {
				list.add(session);
			}
		}
		list.addAll( ops.values() );
		for( Session s : list ) {
			System.out.println("RedisSessionDao:getActiveSessions has gotton cache and redis session -> " + s.getId() + " : " + s.toString() );
		}
		return list;
	}

	@Override
	protected Serializable doCreate(Session session) {
		System.out.println("RedisSessionDao:doCreate will create session -> " + session.getId() + " : " + session.toString());
		/*
		 * Serializable sessionId = generateSessionId(session); 
		 * assignSessionId(session, sessionId);
		 */
		Serializable sessionId = super.doCreate(session);
		redisTemplate.boundHashOps(KEY).put(sessionId.toString(), session);
		System.out.println("RedisSessionDao:doCreate has created session -> " + session.getId() + " : " + session.toString());
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// 先从缓存中获取session，如果没有再去数据库中获取
		System.out.println("RedisSessionDao:doReadSession will read session " + sessionId);
		Session session = super.doReadSession(sessionId);
		if (session == null) {
			System.out.println("RedisSessionDao:doReadSession reading session...");
			session = (Session) redisTemplate.boundHashOps(KEY).get(sessionId.toString());
			System.out.println("RedisSessionDao:doReadSession has read session -> " + session.getId() + " : " + session.toString() );
		}
		return session;
	}

	@Override
	public void doUpdate(Session session) throws UnknownSessionException {
		super.doUpdate(session);//父类实际上什么都没做
		System.out.println("RedisSessionDao:doUpdate will update session -> " + session.getId() + " : " + session.toString());
		if (session instanceof ValidatingSession && !( (ValidatingSession) session ).isValid()) {
			return;
		}
		if (session instanceof ShiroSession) {
			// 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
			ShiroSession shiroSession = (ShiroSession) session;
			if ( !shiroSession.isChanged() ) {
				return;
			}
			System.out.println("RedisSessionDao:doUpdate updateing session -> " + session.getId() + " : " + session.toString());
			Serializable sessionId = session.getId();
			redisTemplate.boundHashOps(KEY).put(sessionId.toString(), session);
			System.out.println("RedisSessionDao:doUpdate has updated session -> " + session.getId() + " : " + session.toString());
			shiroSession.setChanged(false);
		}
	}

	@Override
	public void doDelete(Session session) {
		System.out.println("RedisSessionDao:doDelete will delete session -> "  + session.getId() + " : " + session.toString());
		super.doDelete(session);//父类实际上什么都没做
		if (session != null && session.getId() != null) {
			System.out.println("RedisSessionDao:doDelete deleting session -> "  + session.getId() + " : " + session.toString());
			redisTemplate.boundHashOps(KEY).delete(session.getId().toString());
			System.out.println("RedisSessionDao:doDelete has deleted session -> "  + session.getId() + " : " + session.toString());
		}
	}

}
