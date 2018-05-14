package com.bit.acc.service.intfs;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.baseservice.Service;

import java.util.Date;
import java.util.List;

public interface UserService extends Service<SysUser, Long> {
	
	//public void persist(SysUser transientInstance);
	
	public List<SysUser> findByAccount(String account);
	
	public List<SysUser> findByAccountNickName(String mobileOrEmailOrNickName ) throws Exception;
	
	public Long[][] getNewUsersAndTotalUsersByDate(Date from, Date to);

	public Object[] getNewUsersByDate(Date from, Date to);
	
}
