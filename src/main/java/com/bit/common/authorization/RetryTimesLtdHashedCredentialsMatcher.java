/**
 * 2016年6月8日
 * @author Zhou Liang
 */
package com.bit.common.authorization;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.bit.common.util.IConstants;

/**
 * 密码匹配器，限制密码的匹配次数
 */
public class RetryTimesLtdHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
	
	private Cache<String, AtomicInteger> passwordRetryCache;
	
	public RetryTimesLtdHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
        		retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > IConstants.PASSWORD_RETRY_LIMIT_TIMES) {
            //throw new ExcessiveAttemptsException();//TODO
        }

        boolean matches = super.doCredentialsMatch(token, info);
        //matches = true;//暂时用
        if(matches) {
            //clear retry count
            //passwordRetryCache.remove(username);
        		retryCount = null;
        }
        return matches;
    }

}
