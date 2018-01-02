/**
 * 
 */
package com.bit.websocket;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * @author ZL
 *
 */
public class HandshakeHandler extends DefaultHandshakeHandler {

	/**
	 * 
	 */
	public HandshakeHandler() {
	}

	/**
	 * @param requestUpgradeStrategy
	 */
	public HandshakeHandler(RequestUpgradeStrategy requestUpgradeStrategy) {
		super(requestUpgradeStrategy);
	}

	@Override
	protected Principal determineUser(ServerHttpRequest request,
			WebSocketHandler wsHandler, Map<String, Object> attributes) {

		return super.determineUser(request, wsHandler, attributes);
	}
	
}
