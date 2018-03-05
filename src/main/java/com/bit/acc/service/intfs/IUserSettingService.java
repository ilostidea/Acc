package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.UserSetting;
import com.bit.common.db.IOperations;

public interface IUserSettingService extends IOperations<UserSetting> {
	
	public List<UserSetting> queryByUser( long userID);

}
