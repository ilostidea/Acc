package com.bit.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.SysLog;

public interface SysLogRepository extends JpaRepository<SysLog, Long> {

}
