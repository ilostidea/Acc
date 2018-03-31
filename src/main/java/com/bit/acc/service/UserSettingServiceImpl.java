package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.UserSettingRepository;
import com.bit.acc.model.UserSetting;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.UserSettingService;

@Service("userSettingService")
public class UserSettingServiceImpl extends AbstractService<UserSetting, Long> implements UserSettingService {
	
	@Autowired
	private UserSettingRepository dao;
	
	protected JpaRepository<UserSetting, Long> getDao(){
		return this.dao;
	}

	public List<UserSetting> queryByUser(Long userId){
		return dao.findByUserId(userId);
	}
	
}
