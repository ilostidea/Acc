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
    @Query(value = " select t.OprtTime, count(t.cookieCount) from " +
            " (select l.OprtTime, count(l.Cookie) as cookieCount from SysLog l where l.OprtTime BETWEEN ?1 AND ?2 group by date_format(l.OprtTime, '%Y%m%d'), l.Cookie having count( l.Cookie) = 1) as t " +
            " group by date_format(t.OprtTime, '%Y%m%d')",
            nativeQuery = true)
    public  List<Object[]> getOnlyOnceVisitCookieCountByDate(Date from, Date to);

    /**
     * 用原生SQL，按天数查询一定时期内的留存数
     * @param from
     * @param to
     * @return
     */
    @Query(value = "select t.dateDiff, count(distinct t.retentionCookie) from " +
            "( select DATEDIFF(l1.OprtTime, l2.OprtTime) as dateDiff, l1.Cookie as retentionCookie " +
            "    from SysLog l1 join SysLog l2 on l1.Cookie = l2.Cookie and date_format(l1.OprtTime,'%Y%m%d') > date_format(l2.OprtTime,'%Y%m%d') " +
            "  where date_format(l2.OprtTime,'%Y%m%d') = date_format(?1,'%Y%m%d') and l1.OprtTime <= ?2 " +
            ") as t " +
            "group by t.datediff",
           nativeQuery = true)
    public  List<Object[]> getRetetion(Date from, Date to);

}
