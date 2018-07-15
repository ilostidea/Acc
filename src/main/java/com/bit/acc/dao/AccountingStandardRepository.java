package com.bit.acc.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bit.acc.model.AccountingStandard;

public interface AccountingStandardRepository extends JpaRepository<AccountingStandard, Long> {
	
	@Query("select t from AccountingStandard t where t.status is 1")
//	@Query("select t from AccountingStandard t")
	@QueryHints({ @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value ="true") })
	 <S extends AccountingStandard> List<S> findAllStatusTrue( );
	
}
