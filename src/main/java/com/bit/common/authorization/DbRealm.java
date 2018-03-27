/**
 * 2016年6月7日
 * @author Zhou Liang
 */
package com.bit.common.authorization;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.UserService;

public class DbRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(DbRealm.class);
	
    @Resource(name="sysUserService")
    private UserService sysUserService;
    
    public void setUserService(UserService userService) {
        this.sysUserService = userService;
    }

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken) throws AuthenticationException {
		AuthenticationInfo authcInfo = null;
		//获取基于用户名和密码的令牌
        //authcToken是从UserController里面user.login(token)传过来的
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        logger.info("当前Subject获取到token为{}", authcToken != null ? authcToken.getPrincipal() : "anon");
        String username = String.valueOf( token.getUsername() );
        List<SysUser> userList = sysUserService.findByAccount( username );
        if (userList == null || userList.size() == 0 || userList.get(0) == null) {
            throw new UnknownAccountException();
        }
        
		SysUser sysUser = userList.get(0);
		if( username.contains("@") ){
			authcInfo = new SimpleAuthenticationInfo(
					sysUser.getEmail(),
					sysUser.getPasswd(),
					getName()
					);
		}else{
			authcInfo = new SimpleAuthenticationInfo(
					sysUser.getMobile(),
					sysUser.getPasswd(),
					getName()
					);
		}
		//this.setSession("currentUser", sysUser);
		return authcInfo;
	}
	
	/**
	 * 每次访问需授权资源时都会执行该方法中的逻辑,默认并未启用AuthorizationCache
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		 * 当前的用户名是邮箱或者手机
		 */
        String currentAccount = (String) super.getAvailablePrincipal(principals);
        
/*		List<String> roleList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();
		// 从数据库中获取当前登录用户的详细信息
		User user = userService.getByUsername(currentUsername);
		if (null != user) {
			// 实体类User中包含有用户角色的实体类信息
			if (null != user.getRoles() && user.getRoles().size() > 0) {
				// 获取当前登录用户的角色
				for (Role role : user.getRoles()) {
					roleList.add(role.getName());
					// 实体类Role中包含有角色权限的实体类信息
					if (null != role.getPermissions() && role.getPermissions().size() > 0) {
						// 获取权限
						for (Permission pmss : role.getPermissions()) {
							if (!StringUtils.isEmpty(pmss.getPermission())) {
								permissionList.add(pmss.getPermission());
							}
						}
					}
				}
			}
		} else {
			throw new AuthorizationException();
		}
		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addRoles(roleList);
		simpleAuthorInfo.addStringPermissions(permissionList);*/
        
       // List<SysUser> userList = userService.findByAccount( currentAccount );
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得
       // if(userList != null && userList.size()>0){
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            //simpleAuthorInfo.addRole("admin");
            //添加权限
            //simpleAuthorInfo.addStringPermission("admin:manage");
			logger.info("已为用户{}赋予了{}角色和{}权限",
				currentAccount,
				String.valueOf( simpleAuthorInfo.getRoles() ),
				String.valueOf( simpleAuthorInfo.getStringPermissions() )
				);
            return simpleAuthorInfo;
       // }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见spring-shiro.xml中的<bean id="shiroFilter">的配置
        //return null;
	}

}
