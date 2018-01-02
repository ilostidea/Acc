package com.bit.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 
 * @author ZL
 * 用於發送、接收STOMP消息
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

	public WebSocketBrokerConfig() {
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//定义了一个客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
		registry.enableSimpleBroker("/send","/user");
        //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀
		registry.setApplicationDestinationPrefixes("/receive");
        registry.setUserDestinationPrefix("/user");  
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		/*添加一个/chat端点，客户端就可以通过这个端点来进行连接；withSockJS作用是添加SockJS支持
		 *这个路径与之前发送和接收消息的目的路径有所不同， 这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
		 *即用户发送请求 ：url='/127.0.0.1:8080/websocket'与 STOMP server 进行连接，之后再转发到订阅url
		 */
        registry.addEndpoint("/websocket").setHandshakeHandler(new HandshakeHandler()).withSockJS();
	}

}