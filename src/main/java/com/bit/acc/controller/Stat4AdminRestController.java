package com.bit.acc.controller;

import com.bit.acc.service.intfs.SysLogService;
import com.bit.acc.service.intfs.UserService;
import com.bit.common.model.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/stat")
public class Stat4AdminRestController {

    @Resource(name = "sysUserService")
    private UserService userService;

    @Resource(name = "sysLogService")
    private SysLogService logService;

    private Date getFromDate(Date to, int afterDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(to);
        calendar.add(Calendar.DATE, afterDays);
        return calendar.getTime();
    }

    /**
     * 获得日期，一天最后的时分秒
     * @return
     */
    private Date getEndDate(Date to){
        if (to == null) {
            to = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( to );
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 獲得日註冊客戶數及累計註冊客戶數
     */
    @RequestMapping(value = "/admin/reg", method = RequestMethod.GET)
    public Response stat(@RequestParam(value = "from", required = false) Date from,
                         @RequestParam(value = "to", required = false) Date to) {
        // 测试异常处理 if(true) throw new SQLException("SQL异常");
        to = getEndDate(to);
        if (from == null) {
            from = getFromDate(to, -30);
        }
        Long[][] userList = userService.getNewUsersAndTotalUsersByDate(from, to);
        return new Response().success(userList);
    }

    /**
     * 獲得日访问的IP数及访客数（按Cookie算）
     */
    @RequestMapping(value = "/admin/visit", method = RequestMethod.GET)
    public Response visit(@RequestParam(value = "from", required = false) Date from,
                          @RequestParam(value = "to", required = false) Date to) {
        to = getEndDate(to);
        if (from == null) {
            from = getFromDate(to, -30);
        }
        Long[][] visitList = logService.getVisitIPsAndCookiesByDate(from, to);
        return new Response().success(visitList);
    }

    /**
     * 獲得转换的漏斗图
     */
    @RequestMapping(value = "/admin/convert", method = RequestMethod.GET)
    public Response convert(@RequestParam(value = "from", required = false) Date from,
                          @RequestParam(value = "to", required = false) Date to) {
        to = getEndDate(to);
        if (from == null) {
            from = getFromDate(to, -30);
        }
        Object[] visits = logService.getVisitAllAndNewByDate(from, to);
        Object[] newUsers = userService.getNewUsersByPeriod(from, to);
        Long[] result = new Long[3];
        result[0] = (Long) ((Object[]) visits[0])[1];
        result[1] = (Long) ((Object[]) visits[0])[2];
        result[2] = (Long) ((Object[]) newUsers[0])[1];
        return new Response().success(result);
    }

    /**
     * 跳出率统计
     */
    @RequestMapping(value = "/admin/bounce", method = RequestMethod.GET)
    public Response bounce(@RequestParam(value = "from", required = false) Date from,
                            @RequestParam(value = "to", required = false) Date to) {
        to = getEndDate(to);
        if (from == null) {
            from = getFromDate(to, -30);
        }
        Long[][] visits = logService.getVisitCookieAndOnceVisitCookieByDate(from, to);
        return new Response().success(visits);
    }

    /**
     * 留存率率统计
     */
    @RequestMapping(value = "/admin/retention", method = RequestMethod.GET)
    public Response retention(@RequestParam(value = "from", required = false) Date from,
                           @RequestParam(value = "to", required = false) Date to) {
        from = getEndDate(from);
        if (to == null) {
            to = getFromDate(from, 30);
        }
        Long[][] retention = logService.getRestionByDate(from, to);
        return new Response().success(retention);
    }

}
