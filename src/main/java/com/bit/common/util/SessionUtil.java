package com.bit.common.util;

import com.bit.acc.model.SysUser;
import org.apache.shiro.SecurityUtils;

public class SessionUtil {

    public static Long getCurrentUser( ) {
        Long userId = 0l;
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user != null)
            userId = user.getId();
        return userId;
    }

}
