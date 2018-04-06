package com.bit.acc.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bit.acc.model.GeneralPrinciple;

public interface GeneralPrincipleRepository extends JpaRepository<GeneralPrinciple, Long> {

	@Query("select t from GeneralPrinciple t where t.accountingStandard.id = ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<GeneralPrinciple> findByAccountingStandardId(Long accountingStandardId);

	@Query("select t from GeneralPrinciple t where t.accountingStandard.code = ?1 and t.accountingStandard.exeYear = ?2 and t.status is true")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<GeneralPrinciple> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);

}
