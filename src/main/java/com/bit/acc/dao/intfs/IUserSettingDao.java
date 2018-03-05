package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.UserSetting;
import com.bit.common.db.IOperations;

public interface IUserSettingDao extends IOperations<UserSetting> {
	
	public List<UserSetting> queryByUser( long userID);
	
}
