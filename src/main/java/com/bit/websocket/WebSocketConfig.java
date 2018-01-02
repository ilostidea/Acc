/**
 * 
 */
package com.bit.websocket;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author ZL
 *
 */
@Configuration
@EnableWebSocket
@EnableWebMvc
public class WebSocketConfig implements WebSocketConfigurer {

	@Resource
	WebSocketHandler handler;
	
	/**
	 * 
	 */
	public WebSocketConfig() {
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer#registerWebSocketHandlers(org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry)
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/websck").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(handler, "/websck/sockjs").addInterceptors(new HandshakeInterceptor()).withSockJS();
	}

}
