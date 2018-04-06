package com.bit.common.auth;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

public class ShiroSessionFactory implements SessionFactory {

	@Override
	public Session createSession(SessionContext initData) {
        /*ShiroSession session = new ShiroSession();    
        HttpServletRequest request = (HttpServletRequest)initData.get(DefaultWebSessionContext.class.getName() + ".SERVLET_REQUEST");    
        session.setHost(getIpAddress(request));    
        return session;    */
        if (initData != null) {
            String host = initData.getHost();
            if (host != null) {
                return new ShiroSession(host);
            }
        }
        return new ShiroSession();
	}

    /*
    public static String getIpAddress(HttpServletRequest request) {    
        String localIP = "127.0.0.1";    
        String ip = request.getHeader("x-forwarded-for");    
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("WL-Proxy-Client-IP");    
        }    
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getRemoteAddr();    
        }    
        return ip;    
    }    */
}
