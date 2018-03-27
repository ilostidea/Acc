package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.acc.model.COA;

public interface COARepository extends JpaRepository<COA, Long> {
	
	public List<COA> findByAccountingStandardId(Object accountingStandardID);
	
	@Query("select t from COA  as t left join fetch t.accUsages where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear")
	public List<COA> findByAccStandard(@Param("accountingStandardCode") String accountingStandardCode, @Param("exeYear") int exeYear);
	
	@Query("select t from COA  as t left join fetch t.accUsages where t.accountingStandard.code = :accountingStandardCode and t.accountingStandard.exeYear = :exeYear and t.elementCode = :elementCode")
	public List<COA> findByAccStandardElement(@Param("accountingStandardCode") String accountingStandardCode, @Param("exeYear") int exeYear, @Param("elementCode") String elementCode) ;

}
