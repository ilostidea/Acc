package com.bit.acc.dao.intfs;

import java.util.List;

import com.bit.acc.model.SysUser;
import com.bit.common.db.IOperations;

public interface ISysUserDao extends IOperations<SysUser> {
	
	public List<SysUser> findByAccount(String account);
	
}
