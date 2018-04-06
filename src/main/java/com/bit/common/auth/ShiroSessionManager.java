package com.bit.common.auth;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 自定义WebSessionManager，用于替代DefaultWebSessionManager；
 * 解决：
 *      在shiro的一次认证过程中会调用10次左右的 doReadSession，如果使用内存缓存这个问题不大。
 *      但是如果使用redis，而且子网络情况不是特别好的情况下这就成为问题了。Goma简单在他的环境下测试了一下，
 *      一次redis请求需要80 ~ 100 ms， 一下来10次，一次认证就需要10 * 100 = 1000 ms, 这个就是我们无法接受的了。
 *
 * @author Goma oma1989@yeah.net  2016.03.31
 * 
 * Goma 将大量代码从DefaultWebSessionManager复制到本类中，只需要复写其中的retrieveSession就可以。
 * @author ZL
 *
 */
public class ShiroSessionManager extends DefaultWebSessionManager {
 
	private static final Logger log = LoggerFactory.getLogger(ShiroSessionManager.class);
	
    /**
     * Modify By Goma
     */
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
		if (sessionId == null) {
			log.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a "
					+ "session could not be found.", sessionKey);
			return null;
		}

		// ***************Add By Goma****************
		ServletRequest request = null;
		if (sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey) sessionKey).getServletRequest();
		}

		if (request != null) {
			Object s = request.getAttribute(sessionId.toString());
			if (s != null) {
				return (Session) s;
			}
		}
		// ***************Add By Goma****************

		Session s = retrieveSessionFromDataSource(sessionId);
		if (s == null) {
			// session ID was provided, meaning one is expected to be found, but
			// we couldn't find one:
			String msg = "Could not find session with ID [" + sessionId + "]";
			throw new UnknownSessionException(msg);
		}

		// ***************Add By Goma****************
		if (request != null) {
			request.setAttribute(sessionId.toString(), s);
		}
		// ***************Add By Goma****************

		return s;
    }

}
