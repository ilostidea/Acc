package com.bit.acc.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bit.acc.model.AccElement;

public interface AccElementRepository extends JpaRepository<AccElement, Long> {

	@Query("select t from AccElement t where t.accountingStandard.id = ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<AccElement> findByAccountingStandardId(Long accountingStandardId) ;

	@Query("select t from AccElement t where t.accountingStandard.code = ?1 and t.accountingStandard.exeYear = ?2 and t.status is true")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<AccElement> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);

}
