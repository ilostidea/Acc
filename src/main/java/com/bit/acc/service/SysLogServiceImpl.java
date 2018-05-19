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
		Long[][] result = new Long[3][(int) ChronoUnit.DAYS.between(from.toInstant(), to.toInstant())+1];
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
        Long[][] result = new Long[3][(int) ChronoUnit.DAYS.between(from.toInstant(), to.toInstant()) + 1];
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

    public Long[][] getRestionByDate(Date from, Date to) {
	    int datediff = (int) ChronoUnit.DAYS.between(from.toInstant(), to.toInstant());
        Long[][] result = new Long[4][datediff];
        List<Object[]> queryResult = dao.getRetetion(from, to);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        calendar.add(Calendar.DATE, 1);
        List<Object[]> basicVisitCountResult = dao.getVisitCookieCountByDate( from, calendar.getTime() );
        Long basicVisitCount = 0l;
        if( basicVisitCountResult.size() > 0)
            basicVisitCount = new Long( basicVisitCountResult.get(0)[1].toString() );
        for(int i=0; i<datediff; i++) {
            result[0][i] = new Long(i+1) ;//天数
            result[1][i] = basicVisitCount;//起始时间的访问数（cookie）
            result[2][i] = 0l;//第i天后的访问数
            result[3][i] = 0l;//第i天后的留存率
            for(int j=0; j<queryResult.size(); j++) {
                Object[] retention = queryResult.get(j);
                if (new Long(retention[0].toString()) == i+1) {
                    //result[0][i] = new Long(retention[0].toString());
                    result[2][i] = new Long(retention[1].toString());//第N天的访问量
                    double point = (double) result[2][i]/basicVisitCount*10000;
                    result[3][i] = Math.round( point );
                }
            }
        }
	    return result;
    }

}
