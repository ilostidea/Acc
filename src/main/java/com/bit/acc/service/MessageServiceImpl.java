package com.bit.acc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.MessageRepository;
import com.bit.acc.model.Message;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.MessageService;

@Service("messageService")
public class MessageServiceImpl extends AbstractService<Message, Long> implements MessageService {

	@Autowired
	private MessageRepository dao;
	
	protected JpaRepository <Message, Long> getDao() {
		return dao;
	}

}
