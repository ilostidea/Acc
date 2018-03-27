package com.bit.acc.service.intfs;

import java.util.List;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.baseservice.Service;

public interface UserService extends Service<SysUser, Long> {
	
	//public void persist(SysUser transientInstance);
	
	public List<SysUser> findByAccount(String account);
	
}
