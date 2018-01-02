package com.bit.websocket;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.bit.acc.model.Message;

@Component
public class WebSocketHandler implements
		org.springframework.web.socket.WebSocketHandler {

	public WebSocketHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
        session.sendMessage(new TextMessage("连接已建立，可以开始聊天了！"));
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;
		Message msg = new Message();
		msg.setContent(message.getPayload().toString());
		session.sendMessage(new TextMessage(msg.getContent()));
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
