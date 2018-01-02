package com.bit.acc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.dao.intfs.ISysUserDao;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.IUserService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

@Service("userService")
public class UserService extends AbstractService<SysUser> implements IUserService{
	
	@Resource(name = "userDao")
	private ISysUserDao dao;
	
	/*@Resource(name = "employeeService")
	private IEmployeeService employeeService;*/

	@Override
	protected IOperations<SysUser> getDao() {
		return this.dao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void persist(SysUser transientInstance) {
		/*Employee employee = transientInstance.getEmployee();
		if ( employee == null){
			employee = new Employee(transientInstance.getNickName(), 'M', transientInstance.isStatus() );
		}
		transientInstance.setEmployee(employee);*/
		super.persist(transientInstance);
	}
	
	public List<SysUser> findByAccount(String account){
		return dao.findByAccount(account);
	}

}
