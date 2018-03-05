package com.bit.acc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.IUserSettingDao;
import com.bit.acc.model.UserSetting;
import com.bit.acc.service.intfs.IUserSettingService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("userSettingService")
public class UserSettingService extends AbstractService<UserSetting> implements IUserSettingService{
	
	@Resource(name = "userSettingDao")
	private IUserSettingDao dao;

	@Override
	protected IOperations<UserSetting> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(UserSetting transientInstance) {
		super.persist(transientInstance);
	}
	
	public List<UserSetting> queryByUser( long userID) {
		return dao.queryByUser(userID);
	}

}
