package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.SysUserRepository;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.UserService;

@Service("sysUserService")
public class UserServiceImpl extends AbstractService<SysUser, Long> implements UserService {
	
	@Autowired
	private SysUserRepository dao;
	
	protected JpaRepository <SysUser, Long> getDao() {
		return this.dao;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public SysUser save(SysUser transientInstance) {
		/*Employee employee = transientInstance.getEmployee();
		if ( employee == null){
			employee = new Employee(transientInstance.getNickName(), 'M', transientInstance.isStatus() );
		}
		transientInstance.setEmployee(employee);*/
		return super.save(transientInstance);
	}
	
	public List<SysUser> findByAccount(String account){
		return dao.findByAccount(account);
	}

}
