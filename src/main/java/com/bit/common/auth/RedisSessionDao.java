package com.bit.common.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisManager redisManager;

	@Override
	public Collection<Session> getActiveSessions() {
        List<Session> list = redisManager.hget();
        return list;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
        if(session == null || session.getId() == null){
            System.out.println("Session is null");
            return;
        }
        Serializable sessionId = session.getId();
        redisManager.hadd(sessionId.toString(), session);
	}

	@Override
	public void delete(Session session) {
        if(session == null || session.getId() == null){
            System.out.println("Session is null");
            return;
        }
        redisManager.hdelete(session.getId().toString());
	}

	@Override
	protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisManager.hadd(sessionId.toString(), session);
        return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
        return redisManager.hget(sessionId.toString());
	}

}
