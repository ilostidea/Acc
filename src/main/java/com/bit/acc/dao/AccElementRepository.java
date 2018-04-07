package com.bit.acc.dao;

import java.util.List;
import java.util.Map;

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

	@Query("select new Map( e.id as accElementId, e.accountingStandard as accountingStandard, e.code as accElementCode, e.name as accElementName, "
			+ "   a.id as accId, a.code as accCode, a.name as accName,  "
			+ "   u.id as usageId, u.usages as usages) "
			+ " from AccElement e left join COA a on a.accountingStandard.id = e.accountingStandard.id and a.elementCode = e.code"
			+ " left join AccUsage u on u.coa.id = a.id"
			+ "  where e.accountingStandard.code = ?1 and e.accountingStandard.exeYear = ?2 and e.status is true and a.status is true and u.status is true ")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	public List<Map<String, ?>> findCoaUsagesByAccountingStandardCodeAndExeYear(String accountingStandardCode, int exeYear);

}
