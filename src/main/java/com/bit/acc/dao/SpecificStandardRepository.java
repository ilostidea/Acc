package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.SpecificStandard;

public interface SpecificStandardRepository extends JpaRepository<SpecificStandard, Long> {
	
	public List<SpecificStandard> findByAccountingStandardId(Long accountingStandardID);
	
	public List<SpecificStandard> findByAccountingStandardCodeAndAccountingStandardExeYear(String accountingStandardCode, int exeYear);
	
	@Query("select new SpecificStandard( t.id, t.isPreface, t.title, " + 
			"					CASE t.isPreface WHEN true THEN t.specifics ELSE null END " + 
			"					) from  SpecificStandard as t where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear")
	public List<SpecificStandard> getTitlesByAccStandard(@Param("accountingStandardCode") String accountingStandardCode, @Param("exeYear") int exeYear) ;

}
