package com.bit.acc.dao;

import com.bit.acc.model.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SysLogRepository extends JpaRepository<SysLog, Long> {

    @Query(" select l.oprtTime, " +
            " count(l.id) as visitCount, " +
            " (select count(distinct t.ip) from SysLog t where FUNCTION('date_format', t.oprtTime, '%Y%m%d') = FUNCTION('date_format', l.oprtTime, '%Y%m%d')) as ipCount, " +
            " (select count(distinct t.cookie) from SysLog t where FUNCTION('date_format', t.oprtTime, '%Y%m%d') = FUNCTION('date_format', l.oprtTime, '%Y%m%d')) as cookieCount " +
            " from SysLog l group by FUNCTION('date_format', l.oprtTime, '%Y%m%d') having l.oprtTime BETWEEN ?1 AND ?2")
    public List<Object[]> getVisitIPsAndCookiesByDate(Date from, Date to);

    @Query(" select l.oprtTime, count(l.id) as visitCountAll, count( CASE WHEN l.userId is null THEN 1 ELSE null END ) as visitCountNew " +
            " from SysLog l where l.oprtTime BETWEEN ?1 AND ?2")
    public Object[] getVisitAllAndNewByDate(Date from, Date to);

    @Query(" select l.oprtTime, count(distinct l.cookie) from SysLog l where l.oprtTime BETWEEN ?1 AND ?2 group by FUNCTION('date_format', l.oprtTime, '%Y%m%d') " )
    public  List<Object[]> getVisitCookieCountByDate(Date from, Date to);

    /**
     * 用JPQL实在没辙，只好用原生的SQL了
     * @param from
     * @param to
     * @return
     */
    @Query(value = " select t.oprtTime, count(t.cookieCount) from " +
            " (select l.oprtTime, count(l.cookie) as cookieCount from SysLog l where l.oprtTime BETWEEN ?1 AND ?2 group by date_format(l.oprtTime, '%Y%m%d'), l.cookie having count( l.cookie) = 1) as t " +
            " group by date_format(t.oprtTime, '%Y%m%d')",
            nativeQuery = true)
    public  List<Object[]> getOnlyOnceVisitCookieCountByDate(Date from, Date to);

}
