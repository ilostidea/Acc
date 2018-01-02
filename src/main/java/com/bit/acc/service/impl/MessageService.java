package com.bit.acc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.IMessageDao;
import com.bit.acc.model.Message;
import com.bit.acc.service.intfs.IMessageService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("messageService")
public class MessageService extends AbstractService<Message> implements IMessageService{
	
	@Resource(name = "messageDao")
	private IMessageDao dao;
	
	/*@Resource(name = "employeeService")
	private IEmployeeService employeeService;*/

	@Override
	protected IOperations<Message> getDao() {
		return this.dao;
	}

}
