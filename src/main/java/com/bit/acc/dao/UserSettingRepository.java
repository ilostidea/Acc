package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.UserSetting;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {

	public List<UserSetting> findByUserId( Long userId);
	
}
