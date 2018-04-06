package com.bit.acc.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.SpecificStandard;

public interface SpecificStandardRepository extends JpaRepository<SpecificStandard, Long> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	Optional<SpecificStandard> findById(Long id) ;

	@Query("select t from SpecificStandard t where t.accountingStandard.id = ?1")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<SpecificStandard> findByAccountingStandardId(Long accountingStandardId);

	@Query("select t from SpecificStandard t where t.accountingStandard.code = ?1 and t.accountingStandard.exeYear = ?2 and t.status is true")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<SpecificStandard> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);
	
	@Query("select new SpecificStandard( t.id, t.isPreface, t.title, " + 
			"					CASE t.isPreface WHEN true THEN t.specifics ELSE null END " + 
			"					) from  SpecificStandard as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear and t.status is true")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<SpecificStandard> getTitlesByAccStandard(@Param("accountingStandardCode") String accountingStandardCode, @Param("exeYear") int exeYear) ;

}
