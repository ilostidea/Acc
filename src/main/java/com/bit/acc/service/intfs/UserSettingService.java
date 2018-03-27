package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.UserSetting;
import com.bit.acc.service.baseservice.Service;

public interface UserSettingService extends Service<UserSetting, Long> {

	public List<UserSetting> queryByUser(Long userID);
	
}
