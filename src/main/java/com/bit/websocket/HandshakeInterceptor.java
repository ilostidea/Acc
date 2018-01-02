/**
 * 
 */
package com.bit.websocket;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author ZL
 *
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	/**
	 * 
	 */
	public HandshakeInterceptor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param attributeNames
	 */
	public HandshakeInterceptor(Collection<String> attributeNames) {
		super(attributeNames);
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		//解决The extension [x-webkit-deflate-frame] is not supported问题
        if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
}
