package com.bit.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.AccountingStandard;

public interface AccountingStandardRepository extends JpaRepository<AccountingStandard, Long> {
	
}
