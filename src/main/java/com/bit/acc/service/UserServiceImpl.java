package com.bit.acc.service;

import com.bit.acc.dao.SysUserRepository;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.UserService;
import com.bit.common.log.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @ServiceLog(value = "根据账号查询用户")
	public List<SysUser> findByAccountNickName(String mobileOrEmailOrNickName ) throws Exception{
    	//if(true) throw new SQLException("SQL异常");
		return dao.findByAccountNickName(mobileOrEmailOrNickName);
	}
	
	public Long[][] getNewUsersAndTotalUsersByDate(Date from, Date to) {
		Long totalUserCount = dao.getTotalUsersByDate(from);

		List<Object[]> queryResult = dao.getNewUsersByDate(from, to);
		Long[][] result = new Long[2][(int) ChronoUnit.DAYS.between(from.toInstant(), to.toInstant()) + 1];
		int i = 0;
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd"); 
		Date curDate = from;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		while(curDate.compareTo(to) < 0){
			Long newUser = 0l;
			for(Object[] dayUser : queryResult ) {
				Date date = (Date) dayUser[0];
				if( fmt.format(curDate).equals(fmt.format(date)) ){
					newUser = (Long)  dayUser[1];
                    totalUserCount = totalUserCount + newUser;
					break;
				}
			}
			result[0][i] = newUser;
			result[1][i++] = totalUserCount;
			calendar.add(Calendar.DATE, 1);
			curDate = calendar.getTime();
		}
		return result;
	}

	public Object[] getNewUsersByPeriod(Date from, Date to) {
		return dao.getNewUsersByPeriod(from, to);
	}

}
