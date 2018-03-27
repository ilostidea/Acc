package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
	
	@Query("select u from SysUser u where u.mobile = :account or u.email = :account")
	public List<SysUser> findByAccount(@Param("account") String mobileOrEmail);
	
}
