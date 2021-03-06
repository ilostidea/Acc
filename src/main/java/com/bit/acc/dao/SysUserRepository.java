package com.bit.acc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
	
	@Query("select u from SysUser u where u.mobile = :account or u.email = :account")
	public List<SysUser> findByAccount(@Param("account") String mobileOrEmail);
	
	@Query("select u from SysUser u where u.mobile like '%'|| :account || '%' or u.email like '%' || :account || '%' or u.nickName like '%' || :account || '%'")
	public List<SysUser> findByAccountNickName(@Param("account") String mobileOrEmailOrNickName );

	@Query(" select u.createTime, count(u.id) as newUsers from SysUser u where u.createTime BETWEEN ?1 AND ?2")
	public Object[] getNewUsersByPeriod(Date from, Date to);

	@Query(" select u.createTime, count(u.id) as newUsers from SysUser u where u.createTime BETWEEN ?1 AND ?2 group by FUNCTION('date_format', u.createTime, '%Y%m%d')")
	public List<Object[]> getNewUsersByDate(Date from, Date to);
	
	@Query(" select count(t.id) from SysUser t where t.createTime <= ?1")
	public Long getTotalUsersByDate(Date date);
	
}
