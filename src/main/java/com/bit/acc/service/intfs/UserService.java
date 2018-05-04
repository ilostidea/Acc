package com.bit.acc.service.intfs;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.baseservice.Service;

public interface UserService extends Service<SysUser, Long> {
	
	//public void persist(SysUser transientInstance);
	
	public List<SysUser> findByAccount(String account);
	
	public List<SysUser> findByAccountNickName(String mobileOrEmailOrNickName ) throws Exception;
	
	public Long[][] getNewUsersAndTotalUsersByDate(Date from, Date to);
	
}
