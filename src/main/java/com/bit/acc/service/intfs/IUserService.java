package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.SysUser;
import com.bit.common.db.IOperations;

public interface IUserService extends IOperations<SysUser> {
	
	public List<SysUser> findByAccount(String account);

}
