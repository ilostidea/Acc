package com.bit.acc.service;

import com.bit.acc.dao.SysLogRepository;
import com.bit.acc.model.SysLog;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl extends AbstractService<SysLog, Long> implements SysLogService {
	
	@Autowired
	private SysLogRepository dao;

	protected JpaRepository<SysLog, Long> getDao() {
		return dao;
	}

    /**
     * 获得from日期到to日期之间的访问数量、IP数量、和Cookie数量
     * @param from
     * @param to
     * @return
     */
	public Long[][] getVisitIPsAndCookiesByDate(Date from, Date to) {
		List<Object[]> queryResult = dao.getVisitIPsAndCookiesByDate(from, to);
		Long[][] result = new Long[3][(int) ChronoUnit.DAYS.between(from.toInstant(), to.toInstant())];
		int i = 0;

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date curDate = from;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		while(curDate.compareTo(to) < 0){
			Long visitCount = 0l;
			Long ipCount = 0l;
			Long cookieCount = 0l;
			for(Object[] dayVisits : queryResult ) {
				Date date = (Date) dayVisits[0];
				if( fmt.format(curDate).equals(fmt.format(date)) ){
                    visitCount = (Long)  dayVisits[1];
                    ipCount = (Long) dayVisits[2];
                    cookieCount = (Long) dayVisits[3];
					break;
				}
			}
			result[0][i] = visitCount;
			result[1][i] = ipCount;
            result[2][i++] = cookieCount;
			calendar.add(Calendar.DATE, 1);
			curDate = calendar.getTime();
		}
		return result;
	}

    /**
     * 获得访问数量及新（未登录）的用户数量
     * @param from
     * @param to
     * @return
     */
	public Object[]  getVisitAllAndNewByDate(Date from, Date to) {
	    return dao.getVisitAllAndNewByDate(from, to);
    }

    /**
     * 获得访问数和只有一条记录的数量，以Cookie作为客户端的计量标准
     * @param from
     * @param to
     * @return
     */
	public Long[][] getVisitCookieAndOnceVisitCookieByDate(Date from, Date to) {
        Long[][] result = new Long[3][(int) ChronoUnit.DAYS.between(from.toInstant(), to.toInstant())];
        int i = 0;
        List<Object[]> visitCookieCountResult = dao.getVisitCookieCountByDate(from, to);
        List<Object[]> oneTimeVisitCookieCountResult = dao.getOnlyOnceVisitCookieCountByDate(from, to);

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Date curDate = from;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        while(curDate.compareTo(to) < 0){
            Long visitCookieCount = 0l;
            Long oneTimeVisitCookieCount = 0l;
            for(Object[] dayVisits : visitCookieCountResult ) {
                Date date = (Date) dayVisits[0];
                if( fmt.format(curDate).equals(fmt.format(date)) ){
                    visitCookieCount = (Long)  dayVisits[1];
                    break;
                }
            }
            for(Object[] dayVisits : oneTimeVisitCookieCountResult ) {
                Date date = (Date) dayVisits[0];
                if( fmt.format(curDate).equals(fmt.format(date)) ){
                    //原生的SQL，返回的count()结果是BigInteger类型的
                    oneTimeVisitCookieCount = ((BigInteger) dayVisits[1]).longValue();
                    break;
                }
            }

            result[0][i] = visitCookieCount;
            result[1][i] = oneTimeVisitCookieCount;
            double point = (double) oneTimeVisitCookieCount/visitCookieCount*10000;
            result[2][i++] = Math.round( point );
            calendar.add(Calendar.DATE, 1);
            curDate = calendar.getTime();
        }
        return result;
	}

}
