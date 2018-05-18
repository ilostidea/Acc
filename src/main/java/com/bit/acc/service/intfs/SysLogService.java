package com.bit.acc.service.intfs;

import com.bit.acc.model.SysLog;
import com.bit.acc.service.baseservice.Service;

import java.util.Date;

public interface SysLogService extends Service<SysLog, Long> {

    public Long[][] getVisitIPsAndCookiesByDate(Date from, Date to);

    public Object[] getVisitAllAndNewByDate(Date from, Date to);

    public Long[][] getVisitCookieAndOnceVisitCookieByDate(Date from, Date to);

    public Long[][] getRestionByDate(Date from, Date to);

}
